package com.osf.test.service.impl;

import java.util.List;

import com.osf.test.dao.CommonCodeDAO;
import com.osf.test.dao.impl.CommonCodeDAOImpl;
import com.osf.test.service.CommonService;
import com.osf.test.vo.CommonCodeVO;

public class CommonCodeService implements CommonService {
	private CommonCodeDAO ccdao= new CommonCodeDAOImpl();
	@Override
	public List<CommonCodeVO> selectCommonCodeList(String ccGroup) {
		return ccdao.selectCommonCodeList(ccGroup);
	}

}
