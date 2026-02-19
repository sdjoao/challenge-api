package com.project.challenge.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.challenge.dto.ChallengeReportDTO;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

    public byte[] gerarRelatorioDesafios(List<ChallengeReportDTO> dados) throws Exception{
        InputStream reportStream = 
                getClass().getResourceAsStream("/reports/challenges_report.jrxml");

        JasperReport relatorioJasper = 
                JasperCompileManager.compileReport(reportStream);

        JRBeanCollectionDataSource baseDeDados = 
                new JRBeanCollectionDataSource(dados);

        JasperPrint relatorioPDF = 
                JasperFillManager.fillReport(
                        relatorioJasper,
                        new HashMap<>(),
                        baseDeDados
                );
        return JasperExportManager.exportReportToPdf(relatorioPDF);
    }
}
