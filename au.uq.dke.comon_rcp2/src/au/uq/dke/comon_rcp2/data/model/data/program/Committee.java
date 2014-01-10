package au.uq.dke.comon_rcp2.data.model.data.program;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.ExternalMember;
import au.uq.dke.comon_rcp2.data.model.data.relatedEntity.InternalMember;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Committee extends Structure	{
	
	private Timestamp establishmentStartTime;
	private Timestamp establishmentEndTime;
	
	public Timestamp getEstablishmentStartTime() {
		return establishmentStartTime;
	}
	public void setEstablishmentStartTime(Timestamp establishmentStartTime) {
		this.establishmentStartTime = establishmentStartTime;
	}
	public Timestamp getEstablishmentEndTime() {
		return establishmentEndTime;
	}
	public void setEstablishmentEndTime(Timestamp establishmentEndTime) {
		this.establishmentEndTime = establishmentEndTime;
	}
	public Set<InternalMember> getInternalMembers() {
		return internalMembers;
	}
	public void setInternalMembers(Set<InternalMember> internalMembers) {
		this.internalMembers = internalMembers;
	}
	public Set<ExternalMember> getExternalMembers() {
		return externalMembers;
	}
	public void setExternalMembers(Set<ExternalMember> externalMembers) {
		this.externalMembers = externalMembers;
	}
	public String getObligation() {
		return obligation;
	}
	public void setObligation(String obligation) {
		this.obligation = obligation;
	}
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<InternalMember> internalMembers = new BasicRecordSet<InternalMember>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ExternalMember> externalMembers = new BasicRecordSet<ExternalMember>();
	
	private String obligation;
	
	public Committee(String name){
		super(name);
	}
	public Committee() {

	}
	

}
