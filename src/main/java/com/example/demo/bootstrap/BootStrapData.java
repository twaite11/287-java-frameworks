package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (partRepository.count() == 0 && productRepository.count() == 0) {
            // Create and save 5 sample parts
            Part fiberglass = new InhousePart("Fiberglass Cloth", 25.0, 50);
            Part stringer = new InhousePart("Wooden Stringer", 10.0, 30);
            Part foam = new OutsourcedPart("Foam Blank Material", 75.0, 20, "Foam Blanks Inc.");
            Part resin = new OutsourcedPart("Resin Epoxy", 50.0, 40, "Resin Supplies Ltd.");
            Part finSet = new OutsourcedPart("Fin Set", 40.0, 100, "Finz Co.");

            partRepository.save(fiberglass);
            partRepository.save(stringer);
            partRepository.save(foam);
            partRepository.save(resin);
            partRepository.save(finSet);

            // Create and save 5 sample products
            Product longboard = new Product("Classic Longboard", 200.0, 5);
            longboard.getParts().add(fiberglass);
            longboard.getParts().add(foam);
            longboard.getParts().add(finSet);

            Product shortboard = new Product("Performance Shortboard", 180.0, 8);
            shortboard.getParts().add(fiberglass);
            shortboard.getParts().add(foam);
            shortboard.getParts().add(resin);

            Product fish = new Product("Fish Funboard", 150.0, 10);
            fish.getParts().add(foam);
            fish.getParts().add(finSet);

            Product gun = new Product( "Gun Board", 250.0, 3);
            gun.getParts().add(foam);
            gun.getParts().add(fiberglass);
            gun.getParts().add(stringer);
            gun.getParts().add(resin);

            Product hybrid = new Product("Hybrid Board", 175.0, 6);
            hybrid.getParts().add(foam);
            hybrid.getParts().add(fiberglass);
            hybrid.getParts().add(stringer);

            productRepository.save(longboard);
            productRepository.save(shortboard);
            productRepository.save(fish);
            productRepository.save(gun);
            productRepository.save(hybrid);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts: " + partRepository.count());
        System.out.println(partRepository.findAll());
    }
}