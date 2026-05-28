package h6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import java.util.List;

import java.util.List;

/**
 * 核心思想：把「要做什麼事」變成一個物件，而不是直接呼叫方法。
 * 
 * UML
 * Client ─→ ConcreteCommand ─→ Receiver
     └→ Invoker
     
   Command：命令介面（通常有 execute()、undo()）
   ConcreteCommand：具體命令實現，綁定 Receiver 與動作
   Receiver：真正執行業務邏輯的物件
   Invoker：負責執行命令（可以支援佇列、宏命令）
   Client：組裝命令與接收者
 */
public class CommandMain {
	
	public static void main(String[] args) {
		// Receiver
		Light livingRoomLight = new Light("客廳");
		Light kitchenLight = new Light("廚房");

		// Concrete Commands
		Command livingRoomOn = new LightOnCommand(livingRoomLight);
		Command livingRoomOff = new LightOffCommand(livingRoomLight);
		Command kitchenOn = new LightOnCommand(kitchenLight);

		// Macro Command
		Command partyMode = new MacroCommand(List.of(livingRoomOn, kitchenOn));

		// Invoker
		RemoteControl remote = new RemoteControl();

		System.out.println("=== 測試命令模式 ===");
		remote.pressButton(livingRoomOn);
		remote.pressButton(kitchenOn);
		remote.pressButton(livingRoomOff);

		System.out.println("\n=== 復原操作 ===");
		remote.undo();
		remote.undo();
		remote.undo();

		System.out.println("\n=== 派對模式 ===");
		remote.pressButton(partyMode);
		remote.undo();
	}
	
}

/** Receiver */
class Light {
	private boolean isOn = false;
	private final String location;

	public Light(String location) {
		this.location = location;
	}

	public void on() {
		isOn = true;
		System.out.println(location + " 燈光 ✅ 已開啟");
	}

	public void off() {
		isOn = false;
		System.out.println(location + " 燈光 ❌ 已關閉");
	}
}

/** Command */
@FunctionalInterface
interface Command {
	void execute();

	default void undo() {
		// default 提供空實現
	}
}

/** Concrete Commands */
final class LightOnCommand implements Command {
	
	/** 綁定 Receiver */
	private final Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.on();
	}

	@Override
	public void undo() {
		light.off();
	}
}

final class LightOffCommand implements Command {
	private final Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.off();
	}

	@Override
	public void undo() {
		light.on();
	}
}

/** 宏命令 (Macro) - 一次執行多個命令 */
final class MacroCommand implements Command {
	private final List<Command> commands;

	public MacroCommand(List<Command> commands) {
		this.commands = List.copyOf(commands); // immutable
	}

	@Override
	public void execute() {
		commands.forEach(Command::execute);
	}

	@Override
	public void undo() {
		for (int i = commands.size() - 1; i >= 0; i--) {
			commands.get(i).undo();
		}
	}
}

/** Invoker */
class RemoteControl {
	private final Deque<Command> history = new ArrayDeque<>();

	public void pressButton(Command command) {
		command.execute();
		history.push(command); // 記錄歷史支援 Undo
	}

	public void undo() {
		if (!history.isEmpty()) {
			Command lastCommand = history.pop();
			lastCommand.undo();
			System.out.println("已復原上一個動作");
		}
	}
}