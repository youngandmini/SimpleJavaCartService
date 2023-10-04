import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        items.put(product, quantity);
    }

    public void removeProduct(Product product, int removeQuantity) {

        //장바구니에 담은 양보다 더 많이 삭제하면 아예 items에서 제거
        if (items.get(product) <= removeQuantity) {
            items.remove(product);
        } else {
            items.put(product, items.get(product) - removeQuantity);
        }
    }

    public void showItems() {
        for (Product product : items.keySet()) {
            System.out.println(product.getName() + " : " + items.get(product) + "개");
        }
    }
}
