package org.epam.tests.unit;

import com.epam.tamentoring.bo.*;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.epam.util.TestDataUtil.getRandomNumberAsInteger;
import static org.epam.util.TestDataUtil.getRandomString;

@ExtendWith(MockitoExtension.class)
class UnitTest {
    static UserAccount userAccount;
    @Mock
    DiscountUtility discountUtility;
    @InjectMocks
    OrderService orderService = new OrderService();

    @BeforeAll
    static void setup() {
        userAccount = getUserForTest();
        createShoppingCartForUser(userAccount);
    }

    @Test()
    @DisplayName("User can get products from the shopping cart")
    void userGetProductsTest() {
        Assertions.assertThat(userAccount.getShoppingCart().getProducts())
                .as("No products were received from the shopping cart")
                .isNotEmpty();
    }

    @Test()
    @DisplayName("User can add products to the shopping cart")
    void userAddProductsTest() {
        Product product = generateProductForTest(1);
        ShoppingCart shoppingCart = userAccount.getShoppingCart();
        shoppingCart.addProductToCart(product);
        Assertions.assertThat(shoppingCart.getProducts())
                .as("Product '%s' was not added to cart", product.getName())
                .contains(product);
        userAccount.setShoppingCart(shoppingCart);
    }

    @Test
    @DisplayName("User can remove products from the shopping cart")
    void userRemoveProductsTest() {
        ShoppingCart shoppingCart = userAccount.getShoppingCart();
        List<Product> productsInCart = shoppingCart.getProducts();
        Assumptions.assumeTrue(CollectionUtils.isNotEmpty(productsInCart),
                "Test is skipped, because shopping cart is empty");
        Product productToRemove = productsInCart.get(0);
        shoppingCart.removeProductFromCart(productToRemove);
        Assertions.assertThat(shoppingCart.getProducts())
                .as("All were not removed from shopping cart")
                .doesNotContain(productToRemove);
    }

    @Test
    @DisplayName("User see ProductNotFoundException on retrieve removed product from the shopping cart")
    void userGetRemovedProductsTest() {
        Product product = generateProductForTest(1);
        int productId = product.getId();
        ShoppingCart shoppingCart = userAccount.getShoppingCart();
        shoppingCart.addProductToCart(product);
        shoppingCart.removeProductFromCart(product);

        Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
                .as("ProductNotFoundException was not thrown when user tried to retrieve removed product")
                .isThrownBy(() -> shoppingCart.getProductById(productId));
    }

    @Test
    @DisplayName("User can get the total price of the shopping cart")
    void getCartTotalPriceForUserTest() {
        ShoppingCart shoppingCart = userAccount.getShoppingCart();
        Assumptions.assumeTrue(CollectionUtils.isNotEmpty(shoppingCart.getProducts()),
                "Test is skipped, because shopping cart is empty");
        Double cartTotalPrice = shoppingCart.getCartTotalPrice();
        Assertions.assertThat(cartTotalPrice)
                .as("Shopping cart total price is not > 0. Current total value: %s", cartTotalPrice)
                .isPositive();
    }


    @Test
    @DisplayName("Check Discount for Mock user")
    void getDiscountForMockUserTest() {
        UserAccount user = new UserAccount();
        user.setName("John");
        user.setSurname("Smith");
        user.setDateOfBirth("1990/10/10");
        createShoppingCartForUser(user);

        Mockito.when(orderService.getDiscountUtility().calculateDiscount(user)).thenReturn(3.0);
        Double userDiscount = orderService.getDiscountUtility().calculateDiscount(user);

        Mockito.verify(discountUtility, Mockito.times(1)).calculateDiscount(user);
        Mockito.verifyNoMoreInteractions(discountUtility);
        Assertions.assertThat(userDiscount)
                .as("Discount for Mock user is not correct")
                .isEqualTo(3);
    }

    private static UserAccount getUserForTest() {
        UserAccount testUser = new UserAccount();
        testUser.setName("Alex");
        testUser.setSurname("Test" + getRandomString(3));
        testUser.setDateOfBirth("2000/06/06");
        return testUser;
    }

    private static Product generateProductForTest(int productQty) {
        Product testProduct = new Product();
        testProduct.setId(getRandomNumberAsInteger(3));
        testProduct.setName("TestProduct_" + getRandomString(3));
        testProduct.setQuantity(productQty);
        testProduct.setPrice(21.00);
        return testProduct;
    }

    private static ShoppingCart createShoppingCartForUser(UserAccount userAccount) {
        List<Product> products = new ArrayList<>() {{
            add(generateProductForTest(2));
        }};
        ShoppingCart shoppingCart = new ShoppingCart(products);
        userAccount.setShoppingCart(shoppingCart);
        return shoppingCart;
    }
}
