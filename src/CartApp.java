import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CartApp {
    private static final String path = "./resource/products.csv";

    public static void main(String[] args) {

        //상품목록 생성
        Set<Product> productSet = new HashSet<>();
        //상품 클래스 생성하여 상품 목록에 넣기 (I/O 스트림 사용하여 CSV로 가져옴)
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                long key = Long.parseLong(split[0]);
                String name = split[1];
                int price = Integer.parseInt(split[2]);

                Product product = new Product(key, name, price);
                productSet.add(product);
            }
        } catch (IOException e) {
            System.out.println("Fail to read file");
            throw new RuntimeException(e);
        }

        //상품 목록 확인
        System.out.println("\n======== 상품 목록 확인 ========");
        for (Product product : productSet) {
            System.out.println(product.getName() + " : " + product.getPrice() + "원");
        }

        //장바구니 생성
        Cart myCart = new Cart();

        //상품을 장바구니에 추가
        Random random = new Random();
        Product removeProduct = null;
        int removeQuantity = 0;
        for (Product product : productSet) {
            if (random.nextBoolean()) {
                int quantity = random.nextInt(9) + 2;
                myCart.addProduct(product, quantity);
                removeProduct = product;    //마지막 상품을 삭제할 상품으로 설정
                removeQuantity = quantity - 1;  //1개만 남기고 삭제하도록
            }
        }
        //장바구니에 담긴 상품들을 출력
        System.out.println("\n======== 장바구니 출력 ========");
        myCart.showItems();

        //상품을 장바구니에서 제거
        myCart.removeProduct(removeProduct, removeQuantity);

        //장바구니에 담긴 상품들을 출력하여 제거되었음을 확인
        System.out.println("\n======== 장바구니 출력 ========");
        myCart.showItems();
    }
}
