package com.example.session.app.cart;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.session.domain.model.Cart;

@Controller
@RequestMapping("cart")
public class CartController {

    // (1)
    @Inject
    Cart cart;

    @ModelAttribute
    CartForm setUpForm() {
        return new CartForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    String viewCart(Model model) {
        return "cart/viewCart";
    }

    @RequestMapping(method = RequestMethod.POST)
    String removeFromCart(@Validated CartForm cartForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            ResultMessages messages = ResultMessages.error()
                    .add("e.st.ca.5001");
            model.addAttribute(messages);
            return viewCart(model);
        }
        cart.remove(cartForm.getRemovedItemsIds()); // (2)
        return "redirect:/cart";
    }
}