package com.oysen.controller;

import com.oysen.bean.*;
import com.oysen.result.R;
import com.oysen.service.EstateService;
import com.oysen.vo.CellMessage;
import com.oysen.vo.UnitMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 房产控制器
 */
@RestController
public class EstateController {

    @Resource
    private EstateService estateService;

    @RequestMapping("/estate/selectCompany")
    public R selectCompany() {
        System.out.println("selectCompany");
        List<TblCompany> companies = estateService.selectCompany();
        return new R(companies);
    }

    @RequestMapping("/estate/insertEstate")
    public R insertEstate(FcEstate fcEstate) {
        System.out.println("insertEstate");
        Integer result = estateService.insertEstate(fcEstate);
        if (result == 0) {
            return new R("房产信息已存在");
        }
        return new R(200,result.toString(),"成功插入数据");
    }

    @RequestMapping("/estate/selectBuilding")
    public R selectBuilding(Integer buildingNumber,String estateCode) {
        System.out.println("selectBuilding");
        List<FcBuilding> fcBuildings = estateService.selectBuilding(buildingNumber, estateCode);
        return new R(fcBuildings);
    }

    @RequestMapping("/estate/updateBuilding")
    public R updateBuilding(FcBuilding fcBuilding) {
        System.out.println("updateBuilding");
        Integer result = estateService.updateBuilding(fcBuilding);
        if (result == 1){
            return new R("数据更新成功");
        }
        return new R("更新数据失败");
    }

    @RequestMapping("/estate/selectUnit")
    public R selectUnit(@RequestBody UnitMessage[] unitMessages) {
        System.out.println("selectUnit");
        List<FcUnit> allUnit = new ArrayList<>();
        for (UnitMessage unitMessage : unitMessages) {
            allUnit.addAll(estateService.selectUnit(unitMessage));
        }
        return new R(allUnit);
    }

    @RequestMapping("/estate/updateUnit")
    public R updateUnit(FcUnit fcUnit) {
        System.out.println("updateUnit");
        Integer result = estateService.updateUnit(fcUnit);
        if (result == 1) {
            return new R("数据更新成功");
        }
        return new R("数据更新失败");
    }

    @RequestMapping("/estate/insertCell")
    public R insertCell(@RequestBody CellMessage[] cellMessages) {
        System.out.println("insertCell");
        List<FcCell> allFcCell = new ArrayList<>();
        for (CellMessage cellMessage : cellMessages) {
            allFcCell.addAll(estateService.insertCell(cellMessage));
        }
        return new R(allFcCell);
    }

    @RequestMapping("/estate/selectBuildingByEstate")
    public R selectBuildingByEstate(String estateCode) {
        System.out.println("selectBuildingByEstate");
        List<FcBuilding> fcBuildings = estateService.selectBuildingByEstate(estateCode);
        return new R(fcBuildings);
    }

    @RequestMapping("/estate/selectUnitByBuildingCode")
    public R selectUnitByBuildingCode(String buildingCode) {
        System.out.println("selectUnitByBuildingCode");
        List<FcUnit> fcUnits = estateService.selectUnitByBuildingCode(buildingCode);
        return new R(fcUnits);
    }

    @RequestMapping("/estate/selectCell")
    public R selectCell(String unitCode) {
        System.out.println("selectCell");
        List<FcCell> fcCells = estateService.selectCell(unitCode);
        return new R(fcCells);
    }

    @RequestMapping("/estate/selectEstate")
    public R selectEstate(String company) {
        System.out.println("selectEstate");
        List<FcEstate> estates = estateService.selectEstate(company);
        return new R(estates);
    }

    @RequestMapping("/estate/selectAllEstate")
    public R selectAllEstate() {
        System.out.println("selectAllEstate");
        List<FcEstate> estates = estateService.selectAllEstate();
        return new R(estates);
    }

    @RequestMapping("/estate/selectBuildingByEstateCode")
    public R selectBuildingByEstateCode(String estateCode) {
        System.out.println("selectBuildingByEstateCode");
        List<FcBuilding> fcBuildings = estateService.selectBuildingByEstateCode(estateCode);
        return new R(fcBuildings);
    }

    @RequestMapping("/estate/selectBuildingByEstateCodeAndBuildingCode")
    public R selectBuildingByEstateCodeAndBuildingCode(String estateCode,String buildingCode) {
        System.out.println("selectBuildingByEstateCodeAndBuildingCode");
        FcBuilding fcBuilding = estateService.selectBuildingByEstateCodeAndBuildingCode(estateCode, buildingCode);
        return new R(fcBuilding);
    }
}
