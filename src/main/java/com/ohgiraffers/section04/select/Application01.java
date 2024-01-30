package com.ohgiraffers.section04.select;

import com.ohgiraffers.model.dto.DepartmentDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {
        Connection con = getConnection();
        Properties prop = new Properties();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        DepartmentDTO departmentDTO = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("부서 아이디를 입력해주세요");
        String dept_id=sc.nextLine();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/connection-info.xml"));
            String sql = prop.getProperty("select1");


            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,dept_id);

            rset = pstmt.executeQuery();

            if(rset.next()){
                departmentDTO = new DepartmentDTO();
                departmentDTO.setDeptId(rset.getString("dept_id"));
                departmentDTO.setDeptTitle(rset.getString("dept_title"));
                departmentDTO.setLocationId(rset.getString("location_id"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
            close(con);
        }
        System.out.println(departmentDTO);
    }
}
