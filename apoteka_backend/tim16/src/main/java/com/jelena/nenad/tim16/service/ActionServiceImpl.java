package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.Promotion;
import com.jelena.nenad.tim16.repository.ActionRepository;
import com.jelena.nenad.tim16.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private MedicationService medicationService;


    @Override
    public Collection<Action> findAll() {
        Collection<Action> actions = actionRepository.findAll();
        return actions;
    }

    @Override
    public Collection<Action> findAll(Long id) {
        Collection<Action> foundActions = new ArrayList<>();
        for(Action a: actionRepository.findAll()){
            if(a.getPharmacyId().equals(id.toString())){
                foundActions.add(a);
            }
        }
        return foundActions;
    }

    @Override
    public Optional<Action> findOne(Long id) {
        Optional<Action> action = actionRepository.findById(id);
        return action;
    }

    @Override
    public Action create(Action action) throws Exception {
        if (action.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        action.setId(Long.valueOf(findAll().size())+1);
        if(!medicationService.findOne(action.getMedicationID()).isPresent()) {
            throw new Exception("Medication id je nepostojeci!");
        }
        int medicationPrice = medicationService.findOne(action.getMedicationID()).get().getPrice();
        action.setPrice(medicationPrice);
        action.setActive(true);
        if(action.getNewPrice() > medicationPrice){
            throw new Exception("Cena promocije ne mozete biti vec od stare");
        }
        Action actionSaved = actionRepository.save(action);
        return actionSaved;
    }

    @Override
    public Action update(Action action) throws Exception {
        Optional<Action> actionToUpdate = findOne(action.getId());
        if (!actionToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        actionToUpdate.get().setName(action.getName());
        actionToUpdate.get().setDescription(action.getDescription());
        actionToUpdate.get().setPrice(action.getPrice());
        actionToUpdate.get().setMedicationID(action.getMedicationID());
        actionToUpdate.get().setNewPrice(action.getNewPrice());

        Action actionSaved = actionRepository.save(actionToUpdate.get());
        return actionSaved;
    }

    @Override
    public void delete(Long id) {
        actionRepository.deleteById(id);
    }
}
