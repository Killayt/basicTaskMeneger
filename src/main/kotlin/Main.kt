package org.example

data class Task(val id: Int, val description: String, var Status: String = "Не выполнена")



class ToDoList {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1

    fun addTask(description: String) {
        if (description.isNotBlank()) {
            val task = Task(nextId, description)
            tasks.add(task)
            nextId += 1
            println("Задача добавлена: $description")
        }
    }

    fun completeTask(id: Int) {
        val task = tasks.find { it.id == id }
        if (task != null) {
            task.Status = "Выполнена"
            println("Задача выполнена:\t" +
                    "ID: ${task.id.toString()}" +
                    " ${task.description}")
        } else {
            println("Задача с ID $id не найдена.")
        }
    }

    fun showTasks() {
        if (tasks.isEmpty()) {
            println("Список задач пуст.")
        } else {
            tasks.forEach { task ->
                val status = when (task.Status) {
                    "Выполнена" -> "Выполнена"
                    "Не выполнена" -> "Не выполнена"
                    else -> "В процессе"
                }
                println("ID: ${task.id}, Описание: ${task.description},\t Статус: $status")
            }
        }
    }

    fun taskInProcess(id: Int) {
        showTasks()// здесь должен быть вывод всех задач
        val task = tasks.find { it.id == id }
        if (task != null) {
            task.Status = "В процессе"
        } else {
            println("Задача с ID ${id.toString()} не найдена.")
        }
    }

    fun removeTask(id: Int) {
        val task = tasks.find { it.id == id }
        if (task != null) {
            tasks.remove(task)
            println("Задача ${task.description} с ID ${task.id} удалена.")
        } else {
            println("Задача с ID $id не найдена.")
        }
    }

}


fun main() {
    val toDoList = ToDoList()
    while (true) {
        println(
            "\n\n1. Показать список задач." +
                    "\n2. Создать задачу" +
                    "\n3. Взять задачу в работу." +
                    "\n4. Отметить выполненной." +
                    "\n5. Удалить задачу"
        )
        println("Выберите: ")
        try {
            when (readln().toInt()) {
                1 -> {
                    toDoList.showTasks()
                }

                2 -> {
                    print("Введите задачу: ")
                    val readDescription = readln()
                    toDoList.addTask(readDescription)
                }

                3 -> {
                    print("ID: ")
                    val selectedId = readln().toInt()
                    toDoList.taskInProcess(selectedId)
                }

                4 -> {
                    print("ID: ")
                    val selectedId = readln().toInt()
                    toDoList.completeTask(selectedId)
                }

                5 -> {
                    print("ID: ")
                    val selectedId = readln().toInt()
                    toDoList.removeTask(selectedId)
                }

                else -> println("Invalid type")
            }

        } catch (e: NumberFormatException) {
            println("Нужно ввести число.")
        }

    }
}