package com.processes.batchblocage;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.processes.batchblocage.primary.*;


@Component
public class MyCustomWriter implements ItemWriter<Transfert> {

	
	private TransfertRepository tR;
	
	public MyCustomWriter(TransfertRepository tR) {
		this.tR = tR;
	}

	@Override
	public void write(List<? extends Transfert> listeT) throws Exception {
		for(Transfert t:listeT){
			if(t!=null){
				//System.out.println("\n transfert bloqué :"+t.getCodePin()+" de montant "+t.getMontant_transfert()+" expirant le"+t.getDelai_de_validite()+" d´emetteur "+t.getEmetteur().getNom()+" "+t.getEmetteur().getPrenom()+" de beneficiaire "+t.getBeneficiaire().getNom()+" "+t.getBeneficiaire().getPrenom());
				System.out.println("\n transfert bloqué :"+t.getId()+" de montant "+t.getMontant_transfert()+" expirant le "+t.getDelai_de_validite());
				tR.save(t);
			}
		}
		
	}

}
