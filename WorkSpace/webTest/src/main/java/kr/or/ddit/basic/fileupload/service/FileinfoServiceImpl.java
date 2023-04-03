package kr.or.ddit.basic.fileupload.service;

import java.util.List;

import kr.or.ddit.basic.fileupload.dao.FileinfoDaoImpl;
import kr.or.ddit.basic.fileupload.dao.IFileinfoDao;
import kr.or.ddit.vo.FileinfoVO;

public class FileinfoServiceImpl implements IFilinfoService {
	private IFileinfoDao dao;
	
	private static FileinfoServiceImpl service;
	
	private FileinfoServiceImpl() {
		dao = FileinfoDaoImpl.getInstance();
	}
	
	public static FileinfoServiceImpl getInstance() {
		if(service==null) service = new FileinfoServiceImpl();
		return service;
	}

	@Override
	public int insertFileinfo(FileinfoVO fileVo) {
		return dao.insertFileinfo(fileVo);
	}

	@Override
	public List<FileinfoVO> getAllFileinfo() {
		return dao.getAllFileinfo();
	}

	@Override
	public FileinfoVO getFileinfo(int fileNo) {
		return dao.getFileinfo(fileNo);
	}

}
