package br.octa.enums;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;


public enum UFEnum {

	AC("12", "Acre"),
	AL("27", "Alagoas"),
	AM("13", "Amazonas"),
	AP("16", "Amap\u00e1"),
	BA("29", "Bahia"),
	CE("23", "Cear\u00e1"),
	DF("53", "Distrito Federal"),
	ES("32", "Esp\u00edrito Santo"),
	GO("52", "Goi\u00e1s"),
	MA("21", "Maranh\u00e3o"),
	MG("31", "Minas Gerais"),
	MS("50", "Mato Grosso do Sul"),
	MT("51", "Mato Grosso"),
	PA("15", "Par\u00e1"),
	PB("25", "Para\u00edba"),
	PE("26", "Pernambuco"),
	PI("22", "Piau\u00ed"),
	PR("41", "Paran\u00e1"),
	RJ("33", "Rio de Janeiro"),
	RN("24", "Rio Grande do Norte"),
	RO("11", "Rond\u00f4nia"),
	RR("14", "Roraima"),
	RS("43", "Rio Grande do Sul"),
	SC("42", "Santa Catarina"),
	SE("28", "Sergipe"),
	SP("35", "S\u00e3o Paulo"),
	TO("17", "Tocantins"),
	EX("0", "Exterior");

	private static final Map<String, UFEnum> codigoMap;

	static {
		Builder<String,UFEnum> builder = ImmutableMap.builder();
		
		for (UFEnum uf : values()) {
			builder.put(uf.getCodigoIbge(), uf);
		}
		codigoMap = builder.build();
	}

	public static UFEnum fromCodigoIbge(int codigo) {
		return codigoMap.get(codigo);
	}

	private final String nome;
	private final String codigoIbge;

	private UFEnum(String codigoIbge, String nome) {
		this.codigoIbge = codigoIbge;
		this.nome = nome;
	}

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public String getNome() {
		return nome;
	}

}
