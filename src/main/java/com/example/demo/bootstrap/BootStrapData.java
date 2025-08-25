package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PartService partService;
    private final ProductService productService;
    private final OutsourcedPartService outsourcedPartService;

    public BootStrapData(PartService partService, ProductService productService, OutsourcedPartService outsourcedPartService) {
        this.partService = partService;
        this.productService = productService;
        this.outsourcedPartService = outsourcedPartService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (partService.findAll().isEmpty() && productService.findAll().isEmpty()) {
            System.out.println("Adding sample data via service layer...");

            // Create and save 5 sample parts
            Part fiberglass = new InhousePart("Fiberglass Cloth", 25.0, 50, 20, 100);
            Part stringer = new InhousePart("Wooden Stringer", 10.0, 60, 50, 100);
            OutsourcedPart foam = new OutsourcedPart("Foam Blank Material", 75.0, 60, 50, 100, "Foam Blanks Inc.");
            OutsourcedPart resin = new OutsourcedPart("Resin Epoxy", 50.0, 60, 50, 100, "Resin Supplies Ltd.");
            OutsourcedPart finSet = new OutsourcedPart("Fin Set", 40.0, 60, 50, 100, "Finz Co.");

            try {
                partService.save(fiberglass);
                partService.save(stringer);
                outsourcedPartService.save(foam);
                outsourcedPartService.save(resin);
                outsourcedPartService.save(finSet);
            } catch (ConstraintViolationException e) {
                System.err.println("Validation failed for one or more parts:");
                e.getConstraintViolations().forEach(violation -> System.err.println(violation.getMessage()));
            }

            // Create and save 5 sample products
            Product longboard = new Product("Classic Longboard", 200.0, 5);
            Set<Part> longboardParts = new HashSet<>();
            longboardParts.add(fiberglass);
            longboardParts.add(foam);
            longboardParts.add(finSet);
            longboard.setParts(longboardParts);

            Product shortboard = new Product("Performance Shortboard", 180.0, 8);
            Set<Part> shortboardParts = new HashSet<>();
            shortboardParts.add(fiberglass);
            shortboardParts.add(foam);
            shortboardParts.add(resin);
            shortboard.setParts(shortboardParts);

            Product fish = new Product("Fish Funboard", 150.0, 10);
            Set<Part> fishParts = new HashSet<>();
            fishParts.add(foam);
            fishParts.add(finSet);
            fish.setParts(fishParts);

            Product gun = new Product("Gun Board", 250.0, 3);
            Set<Part> gunParts = new HashSet<>();
            gunParts.add(foam);
            gunParts.add(fiberglass);
            gunParts.add(stringer);
            gunParts.add(resin);
            gun.setParts(gunParts);

            Product hybrid = new Product("Hybrid Board", 175.0, 6);
            Set<Part> hybridParts = new HashSet<>();
            hybridParts.add(foam);
            hybridParts.add(fiberglass);
            hybridParts.add(stringer);
            hybrid.setParts(hybridParts);

            try {
                productService.save(longboard);
                productService.save(shortboard);
                productService.save(fish);
                productService.save(gun);
                productService.save(hybrid);
            } catch (ConstraintViolationException e) {
                System.err.println("Validation failed for one or more products:");
                e.getConstraintViolations().forEach(violation -> System.err.println(violation.getMessage()));
            }
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productService.findAll().size());
        System.out.println(productService.findAll());
        System.out.println("Number of Parts: " + partService.findAll().size());
        System.out.println(partService.findAll());
    }
}
