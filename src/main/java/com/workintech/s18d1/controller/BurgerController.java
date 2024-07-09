package com.workintech.s18d1.controller;


import com.workintech.s18d1.dao.BurgerDaoImpl;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BurgerController {

    private BurgerDaoImpl burgerDao;

    public BurgerController(BurgerDaoImpl burgerDao) {
        this.burgerDao = burgerDao;
    }

    //[GET]/workintech/burgers => tüm burger listini dönmeli.
    //[GET]/workintech/burgers/{id} => İlgili id deki burger objesini dönmeli.
    //[POST]/workintech/burgers => Bir adet burger objesini veritabanına kaydeder.
    //[PUT]/workintech/burgers/{id} => İlgili id deki burger objesinin değerlerini yeni gelen data ile değiştirir.
    //[DELETE]/workintech/burgers/{id} => İlgili id değerindeki burger objesini veritabanından siler.
    //[GET]/workintech/burgers/findByPrice => RequestBody'de price değerini alır ve BurgerDaoImpl sınıfındaki findByPrice metodunu çağırır.
    //[GET]/workintech/burgers/findByBreadType => RequestBody'de breadType değerini alır ve BurgerDaoImpl sınıfındaki findByBreadType metodunu çağırır.
    //[GET]/workintech/burgers/findByContent => RequestBody'de content değerini alır ve BurgerDaoImpl sınıfındaki findByContent metodunu çağırır.

    @GetMapping("/burgers")
    public List<Burger> getAllBurgers() {
        return burgerDao.findAll();
    }

    @GetMapping("/burgers/{id}")
    public Burger getBurgerById(@PathVariable Long id) {
        return burgerDao.findById(id);
    }

    @PostMapping("/burgers")
    public Burger saveBurger(@RequestBody Burger burger) {
        return burgerDao.save(burger);
    }

    @PutMapping("/burgers/{id}")
    public Burger updateBurger(@PathVariable Long id, @RequestBody Burger burger) {
        Burger burgerToUpdate = burgerDao.findById(id);
        burgerToUpdate.setName(burger.getName());
        burgerToUpdate.setPrice(burger.getPrice());
        burgerToUpdate.setVegan(burger.isVegan());
        burgerToUpdate.setBreadType(burger.getBreadType());
        burgerToUpdate.setContents(burger.getContents());
        return burgerDao.update(burgerToUpdate);
    }

    @DeleteMapping("/burgers/{id}")
    public Burger deleteBurger(@PathVariable Long id) {
        return burgerDao.remove(id);
    }

    @GetMapping("/burgers/findByPrice")
    public List<Burger> findByPrice(@RequestBody Integer price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/burgers/findByBreadType")
    public List<Burger> findByBreadType(@RequestBody BreadType breadType) {
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/burgers/findByContent")
    public List<Burger> findByContent(@RequestBody String content) {
        return burgerDao.findByContent(content);
    }



}
