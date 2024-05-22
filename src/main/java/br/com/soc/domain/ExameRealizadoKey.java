package br.com.soc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExameRealizadoKey implements Serializable {

	private static final long serialVersionUID = 8281188143135568060L;

	@Column(name = "cd_exame")
	Long exameId;

	@Column(name = "cd_funcionario")
	Long funcionarioId;

	@Override
	public int hashCode() {
		return Objects.hash(exameId, funcionarioId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExameRealizadoKey other = (ExameRealizadoKey) obj;
		return Objects.equals(exameId, other.exameId) && Objects.equals(funcionarioId, other.funcionarioId);
	}

}
