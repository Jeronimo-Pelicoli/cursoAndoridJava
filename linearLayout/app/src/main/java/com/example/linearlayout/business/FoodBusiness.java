package com.example.linearlayout.business;

import com.example.linearlayout.constants.FoodConstants;
import com.example.linearlayout.entities.FoodEntity;
import com.example.linearlayout.repository.FoodRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FoodBusiness {

    private FoodRepository mFoodRepository = new FoodRepository();

    public FoodEntity get(int id) {
        return mFoodRepository.get(id);
    }

    public List<FoodEntity> getList(int filter) {

        List<FoodEntity> list = this.mFoodRepository.getList();
        List<FoodEntity> filtered;

//        if(filter == FoodConstants.FILTER.NO_FILTER) {
//            return list;
//        }

        if(filter == FoodConstants.FILTER.CAL_LOW) {
            filtered = list.stream().filter(f -> f.getCalories() <=99).collect(Collectors.toList());
        } else
        if(filter == FoodConstants.FILTER.CAL_MEDIUM) {
            filtered = list.stream().filter(f -> f.getCalories() >= 100 && f.getCalories() <= 199).collect(Collectors.toList());
        } else
        if(filter == FoodConstants.FILTER.CAL_HIGH) {
            filtered = list.stream().filter(f -> f.getCalories() >= 200).collect(Collectors.toList());
        } else {
            return list;
        }


//        for(FoodEntity f : list) {
//            if(filter == FoodConstants.FILTER.CAL_LOW) {
//                if(f.getCalories() <= 99) {
//                    filtered.add(f);
//                }
//            } else if(filter == FoodConstants.FILTER.CAL_MEDIUM) {
//                if(f.getCalories() >= 100 && f.getCalories() <= 199) {
//                    filtered.add(f);
//                }
//            } else if(filter == FoodConstants.FILTER.CAL_HIGH) {
//                if(f.getCalories() >= 200) {
//                    filtered.add(f);
//                }
//            }
//        }
        return filtered;
    }
}
