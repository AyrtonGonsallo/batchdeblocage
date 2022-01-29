package com.processes.batchblocage;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.processes.batchblocage.primary.*;
@Component
public class MyCustomProcessor  implements ItemProcessor<Transfert, Transfert>  {

	@Autowired
	ClientBloquéRepository cbR;
	@Override
	public Transfert process(Transfert t) throws Exception {
		
		System.out.println("\n traitement du transfert :"+t.getId()+" de montant "+t.getMontant_transfert()+" expirant le "+t.getDelai_de_validite()+" d´emetteur "+t.getEmetteur().getNom()+" "+t.getEmetteur().getPrenom()+" de beneficiaire "+t.getBeneficiaire().getNom()+" "+t.getBeneficiaire().getPrenom());
		//transfert expiré
		if(t.getDelai_de_validite().compareTo(new Date())<1  && t.getEtat()==EtatTransfert.à_servir){
			t.setDate_de_blocage(new Date());
			t.setEtat(EtatTransfert.bloqué);
			t.setMotifBlocage("Bloqué après expiration du délai ");
			System.out.println("blocage du transfert pour expiration :"+t.getCodePin()+" de montant "+t.getMontant_transfert()+" expirant le "+t.getDelai_de_validite()+" d´emetteur "+t.getEmetteur().getNom()+" "+t.getEmetteur().getPrenom()+" de beneficiaire "+t.getBeneficiaire().getNom()+" "+t.getBeneficiaire().getPrenom());
			return t;
		}
		//émetteur bloqué
		if(cbR.existsByNom(t.getEmetteur().getNom()) && cbR.existsByPrenom(t.getEmetteur().getPrenom()) && t.getEtat()==EtatTransfert.à_servir){
			t.setDate_de_blocage(new Date());
			t.setEtat(EtatTransfert.bloqué);
			t.setMotifBlocage("Bloqué client donneur d’ordre soupçons  ");
			System.out.println("blocage du transfert :"+t.getId()+" de montant "+t.getMontant_transfert()+" expirant le "+t.getDelai_de_validite()+" d´emetteur "+t.getEmetteur().getNom()+" "+t.getEmetteur().getPrenom()+" de beneficiaire "+t.getBeneficiaire().getNom()+" "+t.getBeneficiaire().getPrenom());
			return t;
		}
		//beneficiaire bloqué
		if(cbR.existsByNom(t.getBeneficiaire().getNom()) && cbR.existsByPrenom(t.getBeneficiaire().getPrenom()) && t.getEtat()==EtatTransfert.à_servir){
			t.setDate_de_blocage(new Date());
			t.setEtat(EtatTransfert.bloqué);
			t.setMotifBlocage("Bloqué client bénéficiaire soupçons ");
			System.out.println("blocage du transfert :"+t.getId()+" de montant "+t.getMontant_transfert()+" expirant le "+t.getDelai_de_validite()+" d´emetteur "+t.getEmetteur().getNom()+" "+t.getEmetteur().getPrenom()+" de beneficiaire "+t.getBeneficiaire().getNom()+" "+t.getBeneficiaire().getPrenom());
			return t;
		}
		return null;
	}

}
