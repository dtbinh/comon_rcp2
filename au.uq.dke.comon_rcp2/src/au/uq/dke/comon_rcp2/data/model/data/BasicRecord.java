package au.uq.dke.comon_rcp2.data.model.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.inspector.annotation.UiHidden;
import org.metawidget.inspector.annotation.UiLarge;

import au.uq.dke.comon_rcp2.data.utils.DatabaseUtils;
import au.uq.dke.comon_rcp2.data.utils.ReflectionUtils;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BasicRecord implements Comparable<BasicRecord> {

	private Collection<BasicRecord> mockDataSet = new HashSet<BasicRecord>();

	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@UiHidden
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String name;

	@Column(unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "id: " + id + "," + "name: " + name;
	}

	/**
	 * update itself using the new object
	 * 
	 * @param o
	 */
	public void update(BasicRecord o) {
		this.setName(o.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (((BasicRecord) obj).getId() == this.getId()) {
			return true;
		}
		return false;
	}

	public int compareById(BasicRecord o) {
		return (int) (this.getId() - o.getId());
	}

	private String discription = "";

	public BasicRecord() {

	}

	public BasicRecord(String name) {
		this.name = name;
	}

	@Column(columnDefinition = "TEXT")
	@UiComesAfter("name")
	@UiLarge
	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public int compareTo(BasicRecord o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@UiHidden
	public String getfieldNameByClass() {
		return ReflectionUtils.getfieldNameByClass(this.getClass());
	}

	public Serializable persist() {
		return DatabaseUtils.getSession().save(this);
	}

	/**
	 * inherit classes should rewrite this
	 * 
	 * @return
	 */
	@UiHidden
	public boolean isTable() {
		return false;
	}
	
	@Transient
	public Collection<BasicRecord> getMockDataSet() {
		return mockDataSet;
	}

	public void setMockDataSet(Collection<BasicRecord> mockDataSet) {
		this.mockDataSet = mockDataSet;
	}

}
