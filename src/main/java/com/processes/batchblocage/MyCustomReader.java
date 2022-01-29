package com.processes.batchblocage;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.processes.batchblocage.primary.*;
@Component
public class MyCustomReader extends JdbcCursorItemReader<Transfert> implements ItemReader<Transfert>{
	@Autowired
	AgentRepository aR;
	@Autowired
	TransfertMultipleRepository tmR;
	@Autowired
	EmetteurRepository eR;
	@Autowired
	BeneficiaireRepository bR;
	@Autowired
	PointDeVenteRepository pdvR;
	public MyCustomReader(@Autowired DataSource primaryDataSource) {
		setDataSource(primaryDataSource);
		setSql("SELECT * FROM transfert");
		setFetchSize(100);
		setRowMapper(new TransfertRowMapper());
	}
	
	public class TransfertRowMapper implements RowMapper<Transfert> {
		@Override
		public Transfert mapRow(ResultSet rs, int rowNum) throws SQLException {
			Transfert transfert  = new Transfert();
			transfert.setId(rs.getLong("id"));
			transfert.setCodePin(rs.getString("codePin"));
			transfert.setComission(rs.getDouble("comission"));
			transfert.setDate_de_blocage(rs.getDate("date_de_blocage"));
			transfert.setDate_de_deblocage(rs.getDate("date_de_deblocage"));
			transfert.setDate_demission(rs.getDate("date_demission"));
			transfert.setDelai_de_desherence(rs.getDate("delai_de_desherence"));
			transfert.setDelai_de_validite(rs.getDate("delai_de_validite"));
			
			if(rs.getString("etat")!=null){
				transfert.setEtat(EtatTransfert.valueOf(rs.getString("etat")) );
			}else{
				transfert.setEtat(null );
			}
			transfert.setFrais(rs.getDouble("frais"));
			transfert.setMontant_operation(rs.getDouble("montant_operation"));
			transfert.setMontant_transfert(rs.getDouble("montant_transfert"));
			
			if(rs.getString("motif")!=null){
				transfert.setMotif(MotifTransfert.valueOf(rs.getString("motif")));
			}else{
				transfert.setMotif(null);
			}
			transfert.setMotifBlocage(rs.getString("motifBlocage"));
			transfert.setMotifRestitution(rs.getString("motifRestitution"));
			transfert.setPays_d_emission(rs.getString("pays_d_emission"));
			transfert.setReference(rs.getString("reference"));
			if(rs.getString("type")!=null){
				transfert.setType(TypeTransfert.valueOf(rs.getString("type")));
			}else{
				transfert.setType(null);
			}
			if(rs.getString("typeFrais")!=null){
				transfert.setTypeFrais(TypeFrais.valueOf(rs.getString("typeFrais")));
			}else{
				transfert.setTypeFrais(null);
			}
			transfert.setAgent(aR.findByIdClient(rs.getLong("agent_id")));
			transfert.setBeneficiaire(bR.findByIdClient(rs.getLong("beneficiaire_id")));
			transfert.setEmetteur(eR.findByIdClient(rs.getLong("emetteur_id")));
			transfert.setLieuDeDemande(pdvR.getById(rs.getLong("lieuDeDemande")));
			transfert.setLieuDeService(pdvR.getById(rs.getLong("lieuDeService")));
			
			/*
			if(rowNum==1){
				System.out.println("colones  ...");
				java.sql.ResultSetMetaData md=rs.getMetaData();
				for(int i=1;i<md.getColumnCount();i++){
					System.out.println(md.getColumnName(i)+" "+ md.getColumnTypeName(i));
				}
			}
			
			
			transfert.setTransfertMultiple(tmR.getById(rs.getLong("transfert_multiple_id ")));*/
			return transfert;
		}
	}
}
