package br.com.soc.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.soc.domain.dto.ExameRealizadoDownload;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CriarArquivoExcel {

	public void criarArquivo(String nomeArquivo, List<ExameRealizadoDownload> exameRealizadoDownloads) {
		log.info("Gerando o arquivo {}", nomeArquivo);
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			FileOutputStream outputStream = new FileOutputStream(nomeArquivo);
			XSSFSheet sheet = workbook.createSheet("Exames Realizados");
			int numeroDaLinha = 0;

			adicionarCabecalho(sheet, numeroDaLinha++);

			for (ExameRealizadoDownload e : exameRealizadoDownloads) {
				var linha = sheet.createRow(numeroDaLinha++);
				adicionarCelula(linha, 0, e.getCdExame().toString());
				adicionarCelula(linha, 1, e.getNmExame());
				adicionarCelula(linha, 2, e.getDtRealizacao().toString());
				adicionarCelula(linha, 3, e.getCdFuncionario().toString());
				adicionarCelula(linha, 4, e.getNmFuncionario());
			}
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			log.error("Arquivo não encontrado: {}", nomeArquivo);
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
			log.error("Erro ao processar o arquivo: {} ", nomeArquivo);
		}
		log.info("Arquivo gerado com sucesso!");
	}

	private void adicionarCabecalho(XSSFSheet planilha, int numeroLinha) {
		Row linha = planilha.createRow(numeroLinha);
		adicionarCelula(linha, 0, "Cd Exame");
		adicionarCelula(linha, 1, "Nm Exame");
		adicionarCelula(linha, 2, "Dt Realizacao");
		adicionarCelula(linha, 3, "Cd Funcionario");
		adicionarCelula(linha, 4, "Nm Funcionario");
	}

	private void adicionarCelula(Row linha, int coluna, String valor) {
		Cell cell = linha.createCell(coluna);
		cell.setCellValue(valor);
	}

}
