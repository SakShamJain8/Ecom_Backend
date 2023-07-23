package com.ecom.Controller;

import com.ecom.Services.CartService;
import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/addToCart")
    public ResponseEntity<CartDto> addToCart(@RequestBody ItemRequest itemRequest , Principal principal){
        CartDto addItem = this.cartService.addItem(itemRequest , "Saksham");
        return new ResponseEntity<CartDto>(addItem , HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getAllCart(Principal principal){
        CartDto getAll= this.cartService.getAllCart(principal.getName());
        return new ResponseEntity<CartDto>(getAll , HttpStatus.ACCEPTED);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable int cartId){
        CartDto cartById= this.cartService.getCartById(cartId);
        return new ResponseEntity<CartDto>(cartById , HttpStatus.FOUND);
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<CartDto> deleteCartItems(@PathVariable int pid, Principal p){
        CartDto remove = this.cartService.removeCartItems(p.getName(), pid);
        return new ResponseEntity<CartDto>(remove , HttpStatus.UPGRADE_REQUIRED);
    }
}
