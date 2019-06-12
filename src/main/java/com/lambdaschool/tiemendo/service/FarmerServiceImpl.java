package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Farmer;
import com.lambdaschool.tiemendo.model.FarmerContact;
import com.lambdaschool.tiemendo.model.FarmerLocation;
import com.lambdaschool.tiemendo.repository.FarmerRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("farmerService")
public class FarmerServiceImpl implements FarmerService {

    FarmerRepository farmerRepo;

    public FarmerServiceImpl(FarmerRepository farmerRepo) {
        // Construction injection over autowired because this field is required.
        this.farmerRepo = farmerRepo;
    }

    @Override
    @ApiImplicitParams({
                   @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query", value = "Results page you want to retrieve (0..N)"),
                   @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
                   @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                     value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")})
    public ArrayList<Farmer> findAllFarmers(Pageable pageable) {
        var farmers = new ArrayList<Farmer>();
        farmerRepo.findAll(pageable).iterator().forEachRemaining(farmers::add);

        return farmers;
    }

    @Override
    public List<Farmer> searchFarmers(String name, String location, boolean isLead) {
        /*
        *      The implementation of this method is subject to change
        *      Should be made pageable
        * */
        List<Farmer> farmers = new ArrayList<>();
        String searchName = "%" + name + "%";
        String searchLocation = "%" + location + "%";
        farmers = farmerRepo.searchFarmers(searchName, searchLocation, isLead);
//        farmerRepo.searchFarmers(name, location, isLead).iterator().forEachRemaining(farmers::add);
        return farmers;
    }

    @Override
    public Farmer findFarmer(long id) {
        return farmerRepo.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Could not find client with id: " + id);
        });
    }

    @Override
    @Transactional
    public Farmer addFarmer(Farmer farmer) {
        return farmerRepo.save(farmer);
    }

    @Override
    @Transactional
    public Farmer updateFarmer(Farmer farmer) {
        /*
        *  This will update the farmer, farmer contact info, and farmer loaction info
        *  to update transactions and installments they will need to be updated directly
        *  from their respective controllers
        */
        Farmer current = findFarmer(farmer.getId());

        if (farmer.getName() != null) {
            current.setName(farmer.getName());
        }
        if (farmer.getStartyear() > 0) {
            current.setStartyear(farmer.getStartyear());
        }
        if (farmer.getFarmercontact() != null) {
            /*
            *
            *    UPDATING FARMER CONTACT INFO HERE
            *
            */
            FarmerContact currentfc = current.getFarmercontact();
            FarmerContact incomingfc = farmer.getFarmercontact();

            if (incomingfc.getDateofbirth() != null)
                currentfc.setDateofbirth(incomingfc.getDateofbirth());

            if (incomingfc.getEducationlevel() != null)
                currentfc.setEducationlevel(incomingfc.getEducationlevel());

            if (incomingfc.getEmail() != null)
                currentfc.setEmail(incomingfc.getEmail());

            if (incomingfc.getGender() != null)
                currentfc.setGender(incomingfc.getGender());

            if (incomingfc.getName() != null)
                currentfc.setName(incomingfc.getName());

            if (incomingfc.getNationality() != null)
                currentfc.setNationality(incomingfc.getNationality());

            if (incomingfc.getPhone() != null)
                currentfc.setPhone(incomingfc.getPhone());

            if (incomingfc.getPosition() != null)
                currentfc.setPosition(incomingfc.getPosition());

            if (incomingfc.getTitle() != null)
                currentfc.setTitle(incomingfc.getTitle());
        }
        if (farmer.getFarmerlocation() != null) {
            /*
             *
             *    UPDATING FARMER LOCATION HERE
             *
             */
            System.out.println("Updating Farmer Location");
            FarmerLocation currentfl = current.getFarmerlocation();
            FarmerLocation incomingfl = farmer.getFarmerlocation();

            if (incomingfl.getAddress() != null)
                currentfl.setAddress(incomingfl.getAddress());

            if (incomingfl.getCommunity() != null)
                currentfl.setCommunity(incomingfl.getCommunity());

            if (incomingfl.getDistrict() != null)
                currentfl.setDistrict(incomingfl.getDistrict());

            if (incomingfl.getLandmark() != null)
                currentfl.setLandmark(incomingfl.getLandmark());

            if (incomingfl.getRegion() != null)
                currentfl.setRegion(incomingfl.getRegion());

        }
        return farmerRepo.save(current);
    }

    @Override
    public void deleteFarmer(long id) {
        Farmer f = findFarmer(id);
        farmerRepo.delete(f);
    }
}
