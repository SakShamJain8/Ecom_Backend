package com.ecom.Services;

import com.ecom.Exception.ResourceNotFoundException;
import com.ecom.Model.Cart;
import com.ecom.Model.CartItem;
import com.ecom.Model.Product;
import com.ecom.Model.User;
import com.ecom.Repository.CartRepository;
import com.ecom.Repository.ProductRepository;
import com.ecom.Repository.UserRepository;
import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private ModelMapper modelMapper;
    public CartDto addItem(ItemRequest item , String Username){
        int productId=item.getProductId();
        int quantity = item.getQuantity();
        User user = this.userRepo.findByEmail(Username);
        Product product = this.productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
        if(!product.isStock()){
            new ResourceNotFoundException("Out of Stock");
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        double totalPrice = product.getProduct_price() * product.getProduct_quantity();
        cartItem.setTotalPrice(totalPrice);

        Cart cart = user.getCart();
        if(cart == null){
            cart = new Cart();
        }

        cartItem.setCart(cart);
        Set<CartItem> items= cart.getItems();

        //checking item avail in cart or not
        AtomicReference<Boolean> flag = new AtomicReference<>(false);
         Set<CartItem> newProduct = items.stream().map((i) -> {
            if(i.getProduct().getProduct_id() == product.getProduct_id()){
                i.setQuantity(quantity);
                i.setTotalPrice(totalPrice);
                flag.set(true);
            }
            return i;
        }).collect(Collectors.toSet());

         if(flag.get()){
             items.clear();
             items.addAll(newProduct);
         }else{
             cartItem.setCart(cart);
             items.add(cartItem);
         }

         Cart save= this.cartRepo.save(cart);

        return this.modelMapper.map(save , CartDto.class);
    }

    public CartDto getAllCart(String email){

        User user = this.userRepo.findByEmail(email);
        Cart cart= this.cartRepo.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Cart Not Found"));
        return this.modelMapper.map(cart , CartDto.class);
    }

    public CartDto getCartById(int cartId){

        Cart findByUserAndCartId = this.cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));
        return this.modelMapper.map(findByUserAndCartId , CartDto.class);
    }

    public CartDto removeCartItems(String userName , int productId){
        User user = this.userRepo.findByEmail(userName);
        Cart cart = user.getCart();
        Set<CartItem> items= cart.getItems();
        boolean removeIf = items.removeIf((i) ->i.getProduct().getProduct_id() == productId);
        Cart save = this.cartRepo.save(cart);
        return this.modelMapper.map(save , CartDto.class);
    }
}
