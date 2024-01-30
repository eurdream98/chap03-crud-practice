package com.ohgiraffers.section04.select;

import com.ohgiraffers.model.dto.DepartmentDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        DepartmentDTO departmentDTO = null;
        List<DepartmentDTO> departmentDTOList = null;
        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/connection-info.xml"));
            String query = prop.getProperty("select2");
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            departmentDTOList = new ArrayList<>();
            while(rset.next()){
                departmentDTO = new DepartmentDTO();

                departmentDTO.setDeptId(rset.getString("dept_id"));
                departmentDTO.setDeptTitle(rset.getString("dept_title"));
                departmentDTO.setLocationId(rset.getString("location_id"));

                departmentDTOList.add(departmentDTO);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(rset);
            close(pstmt);
            close(con);
        }
        for(DepartmentDTO a : departmentDTOList){
            System.out.println(a);
        }


    }
}
