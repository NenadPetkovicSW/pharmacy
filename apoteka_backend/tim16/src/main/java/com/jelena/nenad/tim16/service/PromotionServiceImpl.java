package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.Promotion;
import com.jelena.nenad.tim16.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService{

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private MedicationService medicationService;

    @Override
    public Collection<Promotion> findAll() {
        Collection<Promotion> promotions = promotionRepository.findAll();
        return promotions;
    }

    @Override
    public Collection<Promotion> findAll(Long id) {
        Collection<Promotion> foundPromotions = new ArrayList<>();
        for(Promotion a: promotionRepository.findAll()) {
            if (a.getPharmacyId().equals(id.toString())) {
                foundPromotions.add(a);
            }
        }
        return foundPromotions;
    }

    @Override
    public Optional<Promotion> findOne(Long id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        return promotion;
    }

    @Override
    public Promotion create(Promotion p) throws Exception {
        if (p.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        p.setId(Long.valueOf(findAll().size())+1);
        if(!medicationService.findOne(p.getMedicationID()).isPresent()){
            throw new Exception("Medication id je nepostojeci!");
        }
        p.setActive(true);
        int medicationPrice = medicationService.findOne(p.getMedicationID()).get().getPrice();
        if(p.getPrice() > medicationPrice*p.getAmountOfProduct()){
            throw new Exception("Cena promocije ne mozete biti veca od cene leka*kolicina");
        }
        Promotion promotionsaved = promotionRepository.save(p);
        return promotionsaved;
    }

    @Override
    public Promotion update(Promotion p) throws Exception {
        Optional<Promotion> promotionToUpdate = findOne(p.getId());
        if (!promotionToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        promotionToUpdate.get().setName(p.getName());
        promotionToUpdate.get().setDescription(p.getDescription());
        promotionToUpdate.get().setPrice(p.getPrice());
        promotionToUpdate.get().setMedicationID(p.getMedicationID());
        promotionToUpdate.get().setAmountOfProduct(p.getAmountOfProduct());
        promotionToUpdate.get().setActive(p.isActive());
        promotionToUpdate.get().setEndDate(p.getEndDate());

        Promotion promotioSaved = promotionRepository.save(promotionToUpdate.get());
        return promotioSaved;
    }

    @Override
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }
}
