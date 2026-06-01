package h6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskComandMain {

	public static void main(String[] args) {
		TaskManager manager = new TaskManager();
		TaskCommandInvoker invoker = new TaskCommandInvoker();

		Task task1 = new Task("T001", "完成財務報表", "小明", false);
		Task task2 = new Task("T002", "客戶拜訪", "小華", false);

		System.out.println("=== 任務管理系統 ===");

		// 新增任務
		invoker.executeCommand(new AddTaskCommand(manager, task1));
		invoker.executeCommand(new AddTaskCommand(manager, task2));

		// 完成任務
		invoker.executeCommand(new CompleteTaskCommand(manager, "T001", task1));

		// 更新任務
		invoker.executeCommand(
				new UpdateTaskCommand(manager, "T002", task2.title(), task2.assignee(), "重要客戶拜訪 - 簽約", "小華"));

		System.out.println("\n=== 操作復原測試 ===");
		invoker.undo(); // 復原更新
		invoker.undo(); // 復原完成
		invoker.undo(); // 復原新增 task2

		System.out.println("\n=== 重做操作 ===");
		invoker.redo();
		invoker.redo();
	}

}

// ====================== Receiver ======================
record Task(String id, String title, String assignee, boolean completed) {
	public Task complete() {
		return new Task(id, title, assignee, true);
	}

	public Task update(String newTitle, String newAssignee) {
		return new Task(id, newTitle, newAssignee, completed);
	}
}

class TaskManager {
	private final Map<String, Task> tasks = new ConcurrentHashMap<>();

	public void addTask(Task task) {
		tasks.put(task.id(), task);
		System.out.println("✅ 新增任務: " + task.title());
	}

	public void removeTask(String id) {
		Task removed = tasks.remove(id);
		if (removed != null) {
			System.out.println("🗑️ 刪除任務: " + removed.title());
		}
	}

	public void completeTask(String id) {
		tasks.computeIfPresent(id, (k, t) -> {
			Task completed = t.complete();
			System.out.println("✔️ 完成任務: " + completed.title());
			return completed;
		});
	}

	public void updateTask(String id, String newTitle, String newAssignee) {
		tasks.computeIfPresent(id, (k, t) -> {
			Task updated = t.update(newTitle, newAssignee);
			System.out.println("✏️ 更新任務: " + updated.title());
			return updated;
		});
	}

	public Task getTask(String id) {
		return tasks.get(id);
	}
}

// ====================== Command 介面 ======================
interface TaskCommand {
	void execute();

	void undo();
}

//====================== Concrete Commands ======================
record AddTaskCommand(TaskManager manager, Task task) implements TaskCommand {
	@Override
	public void execute() {
		manager.addTask(task);
	}

	@Override
	public void undo() {
		manager.removeTask(task.id());
	}
}

record DeleteTaskCommand(TaskManager manager, Task task) implements TaskCommand {
	@Override
	public void execute() {
		manager.removeTask(task.id());
	}

	@Override
	public void undo() {
		manager.addTask(task);
	}
}

record CompleteTaskCommand(TaskManager manager, String taskId, Task originalTask) implements TaskCommand {
	@Override
	public void execute() {
		manager.completeTask(taskId);
	}

	@Override
	public void undo() {
		// 簡化：把完成狀態改回 false（實際可記錄完整原始狀態）
		Task current = manager.getTask(taskId);
		if (current != null) {
			Task restored = new Task(current.id(), current.title(), current.assignee(), false);
			manager.updateTask(current.id(), restored.title(), restored.assignee());
		}
	}
}

record UpdateTaskCommand(TaskManager manager, String taskId, String oldTitle, String oldAssignee, String newTitle,
		String newAssignee) implements TaskCommand {

	@Override
	public void execute() {
		manager.updateTask(taskId, newTitle, newAssignee);
	}

	@Override
	public void undo() {
		manager.updateTask(taskId, oldTitle, oldAssignee);
	}
}

//====================== Invoker ======================

class TaskCommandInvoker {
	private final Deque<TaskCommand> history = new ArrayDeque<>();
	private final Deque<TaskCommand> redoStack = new ArrayDeque<>();

	public void executeCommand(TaskCommand command) {
		command.execute();
		history.push(command);
		redoStack.clear(); // 執行新指令後清除 redo
		System.out.println("→ 指令已執行");
	}

	public void undo() {
		if (!history.isEmpty()) {
			TaskCommand command = history.pop();
			command.undo();
			redoStack.push(command);
			System.out.println("↩️ 已復原");
		}
	}

	public void redo() {
		if (!redoStack.isEmpty()) {
			TaskCommand command = redoStack.pop();
			command.execute();
			history.push(command);
			System.out.println("↪️ 已重做");
		}
	}
}