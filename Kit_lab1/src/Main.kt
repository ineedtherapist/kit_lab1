data class Item(
    val id: Int,
    var name: String,
    var quantity: Int,
    var price: Double
)

class ShoppingList {
    private val items = mutableListOf<Item>()
    private var idCounter = 1

    fun addItem(name: String, quantity: Int, price: Double) {
        val item = Item(id = idCounter++, name, quantity, price)
        items.add(item)
        println("Товар '${item.name}' додано до списку! Кількість: ${item.quantity}, Ціна за одиницю: ${item.price}")
    }

    fun removeItem(itemId: Int): Boolean {
        val item = items.find { it.id == itemId }
        return if (item != null) {
            items.remove(item)
            println("Товар '${item.name}' видалено зі списку!")
            true
        } else {
            println("Товар із ID $itemId не знайдено!")
            false
        }
    }

    fun editItem(itemId: Int, name: String?, quantity: Int?, price: Double?) {
        val item = items.find { it.id == itemId }
        if (item != null) {
            val oldName = item.name
            val oldQuantity = item.quantity
            val oldPrice = item.price
            name?.let { item.name = it }
            quantity?.let { item.quantity = it }
            price?.let { item.price = it }
            println(
                "Товар із ID ${item.id} змінено:\n" +
                        "- Назва: $oldName -> ${item.name}\n" +
                        "- Кількість: $oldQuantity -> ${item.quantity}\n" +
                        "- Ціна: $oldPrice -> ${item.price}"
            )
        } else {
            println("Товар із ID $itemId не знайдено!")
        }
    }

    fun printReceipt() {
        println("\n*** Чек ***")
        items.forEach { item ->
            println(
                "${item.name} (${item.quantity} x ${item.price}) = ${"%.2f".format(item.quantity * item.price)}"
            )
        }
        val total = items.sumOf { it.quantity * it.price }
        println("Загальна сума: ${"%.2f".format(total)}")
        println("***********\n")
    }

    fun displayItems() {
        if (items.isEmpty()) {
            println("Список покупок порожній!")
        } else {
            println("\nВаш список покупок:")
            items.forEach { item ->
                println(
                    "${item.id}. ${item.name} | Кількість: ${item.quantity} | Ціна за одиницю: ${item.price}"
                )
            }
        }
    }
}

fun main() {
    val shoppingList = ShoppingList()

    // Додавання товарів
    shoppingList.addItem("Молоко", 2, 25.0)
    shoppingList.addItem("Хліб", 1, 15.0)
    shoppingList.addItem("Яблука", 5, 12.0)

    // Відображення товарів
    shoppingList.displayItems()

    // Редагування товару
    shoppingList.editItem(2, name = "Батон", quantity = 2, price = 18.0)

    // Видалення товару
    shoppingList.removeItem(3)

    // Відображення списку після змін
    shoppingList.displayItems()

    // Виведення чеку
    shoppingList.printReceipt()
}
