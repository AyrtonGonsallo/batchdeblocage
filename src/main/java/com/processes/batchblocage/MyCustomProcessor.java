package com.processes.batchblocage;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import com.processes.batchblocage.primary.*;
@Component
public class MyCustomProcessor  implements ItemProcessor<Transfert, Transfert>  {

	@Override
	public Transfert process(Transfert t) throws Exception {
		System.out.println("\n traitement du transfert :"+t.getId()+" de montant "+t.getMontant_transfert()+" expirant le "+t.getDelai_de_validite());
		
		//System.out.println("\n traitement du transfert :"+t.getCodePin()+" de montant "+t.getMontant_transfert()+" expirant le"+t.getDelai_de_validite()+" d´emetteur "+t.getEmetteur().getNom()+" "+t.getEmetteur().getPrenom()+" de beneficiaire "+t.getBeneficiaire().getNom()+" "+t.getBeneficiaire().getPrenom());
		if(t.getDelai_de_validite().compareTo(new Date())<1  && t.getEtat()==EtatTransfert.à_servir){
			System.out.println("\n le transfert :"+t.getId()+" sera bloqué");
			t.setDate_de_blocage(new Date());
			t.setEtat(EtatTransfert.bloque_après_expiration_du_délai);
			//System.out.println("blocage du transfert :"+t.getCodePin()+" de montant "+t.getMontant_transfert()+" expirant le"+t.getDelai_de_validite()+" d´emetteur "+t.getEmetteur().getNom()+" "+t.getEmetteur().getPrenom()+" de beneficiaire "+t.getBeneficiaire().getNom()+" "+t.getBeneficiaire().getPrenom());
			return t;
		}
		return null;
	}

}
