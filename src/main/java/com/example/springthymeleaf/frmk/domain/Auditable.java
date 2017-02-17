package com.example.springthymeleaf.frmk.domain;

import java.io.Serializable;
import java.util.Date;

public interface Auditable<Auditor, ID extends Serializable> extends Identifiable<ID> {

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Auditor getCreator();

	void setCreator(Auditor creator);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	Auditor getUpdater();

	void setUpdater(Auditor updater);
}
