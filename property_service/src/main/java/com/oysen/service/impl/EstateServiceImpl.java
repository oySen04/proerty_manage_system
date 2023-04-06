package com.oysen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oysen.bean.*;
import com.oysen.mapper.*;
import com.oysen.service.EstateService;
import com.oysen.vo.CellMessage;
import com.oysen.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class EstateServiceImpl implements EstateService {
    @Resource
    private TblCompanyMapper companyMapper;
    @Resource
    private FcEstateMapper fcEstateMapper;
    @Resource
    private FcBuildingMapper fcBuildingMapper;
    @Resource
    private FcUnitMapper fcUnitMapper;
    @Resource
    private FcCellMapper fcCellMapper;

    @Override
    public List<TblCompany> selectCompany() {
        List<TblCompany> tblCompanyList = companyMapper.selectCompany();
        return tblCompanyList;
    }

    /**
     * 判断当前住宅编码是否唯一，如果重复则插入失败
     * @param fcEstate
     * @return
     */
    @Override
    public Integer insertEstate(FcEstate fcEstate) {
        Integer result = 0;
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code",fcEstate.getEstateCode());
        FcEstate fe = fcEstateMapper.selectOne(queryWrapper);
        //如果数据不存在则正常插入
        if (fe == null) {
            result = fcEstateMapper.insert(fcEstate);
        }
        return result;
    }

    /**
     * 根据传递的楼宇数量和住宅编码生成对应的楼宇信息并回显
     * @param buildingNumber 楼宇数量
     * @param estateCode 房产(住宅)编码
     * @return 回显的数据
     */
    @Override
    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode) {
        List<FcBuilding> fcBuildings = new ArrayList<>();
        for (int i = 0; i < buildingNumber; i++) {
            FcBuilding fcBuilding = new FcBuilding();
            //添加唯一标识
            //fcBuilding.setBuildingCode("B" + (i + 1));
            fcBuilding.setBuildingCode(estateCode + "B" + (i + 1));
            fcBuilding.setBuildingName("第" + (i + 1) + "号楼");
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
    }

    /**
     * 完成楼宇信息的数据更新
     * @param fcBuilding 用户编写的数据
     * @return
     */
    @Override
    public Integer updateBuilding(FcBuilding fcBuilding) {
        Integer result = fcBuildingMapper.updateById(fcBuilding);
        return result;
    }

    @Override
    public List<FcUnit> selectUnit(UnitMessage unitMessage) {
        //定义返回的集合
        List<FcUnit> fcUnits = new ArrayList<>();
        //插入数据操作
        for (int i = 0; i < unitMessage.getUnitCount(); i++) {
            FcUnit fcUnit = new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            //添加唯一标识
            //fcUnit.setUnitCode("U" + (i + 1));
            fcUnit.setUnitCode(unitMessage.getBuildingCode() + "U" + (i + 1));
            fcUnit.setUnitName("第" + (i + 1) + "单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }

    @Override
    public Integer updateUnit(FcUnit fcUnit) {
        Integer result  = fcUnitMapper.updateById(fcUnit);
        return result;
    }

    @Override
    public List<FcCell> insertCell(CellMessage cellMessage) {
        List<FcCell> fcCells = new ArrayList<>();
        //楼层
        for (int i = 0; i < cellMessage.getStopFloor(); i++) {
            //房间
            for (int j = 0; j < cellMessage.getStopCellId(); j++) {
                FcCell fcCell = new FcCell();
                fcCell.setUnitCode(cellMessage.getUnitCode());//单元编码
                //添加唯一标识
                //fcCell.setCellCode("C" + (i + 1) + "0" + (j + 1));
                fcCell.setCellCode(cellMessage.getUnitCode() + "C" + (i + 1) + "0" + (j + 1));//房间编码
                fcCell.setCellName((i + 1) + "0" + (j + 1));//房间号码
                fcCell.setFloorNumber(cellMessage.getStopFloor());//楼层
                fcCellMapper.insert(fcCell);
                fcCells.add(fcCell);
            }
        }
        return fcCells;
    }

    @Override
    public List<FcBuilding> selectBuildingByEstate(String estateCode) {
        QueryWrapper<FcBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code",estateCode);
        //此方法规定可以查询的字段
        queryWrapper.select("building_code","building_name");
        List<FcBuilding> fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        return fcBuildings;
    }

    @Override
    public List<FcUnit> selectUnitByBuildingCode(String buildingCode) {
        List<FcUnit> fcUnits;
        QueryWrapper<FcUnit> queryWrapper = new QueryWrapper<>();
        if (buildingCode.equals("")){

            queryWrapper.select("*");
            fcUnits = fcUnitMapper.selectList(queryWrapper);
        }else {

            queryWrapper.eq("building_code",buildingCode);
            //queryWrapper.select("unit_code","unit_name");
            fcUnits = fcUnitMapper.selectList(queryWrapper);
        }
        return fcUnits;
    }

    @Override
    public List<FcCell> selectCell(String unitCode) {
        List<FcCell> fcCells;
        QueryWrapper<FcCell> queryWrapper = new QueryWrapper<>();
        if (unitCode.equals("")){
            queryWrapper.select("*");
            fcCells = fcCellMapper.selectList(queryWrapper);
        }else {
            queryWrapper.eq("unit_code",unitCode);
            fcCells = fcCellMapper.selectList(queryWrapper);
        }

        return fcCells;
    }

    @Override
    public List<FcEstate> selectEstate(String company) {
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company",company);
        List<FcEstate> estates = fcEstateMapper.selectList(queryWrapper);
        return estates;
    }

    @Override
    public List<FcEstate> selectAllEstate() {
        List<FcEstate> fcEstates = fcEstateMapper.selectAllEstate();
        return fcEstates;
    }

    /**
     * 此接口可以省略，改用selectBuildingByEstate接口，但需要注意更改业务层功能，改为查询全部数据
     * @param estateCode
     * @return
     */
    @Override
    public List<FcBuilding> selectBuildingByEstateCode(String estateCode) {
        List<FcBuilding> fcBuildings;
        QueryWrapper<FcBuilding> queryWrapper;
        /**
         * 当前端发送请求携带参数时做条件查询如果请求没有具体参数时做全量查询
         */
        if (estateCode.equals("")) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.select("*");
            fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        }else {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("estate_code",estateCode);
            fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        }
        return fcBuildings;
    }

    @Override
    public FcBuilding selectBuildingByEstateCodeAndBuildingCode(String estateCode, String buildingCode) {
        QueryWrapper<FcBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code",estateCode);
        queryWrapper.eq("building_code",buildingCode);
        FcBuilding fcBuilding = fcBuildingMapper.selectOne(queryWrapper);
        return fcBuilding;
    }
}
