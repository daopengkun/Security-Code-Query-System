package cn.xiaodong.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传与下载
 * @author 朱晓东
 *
 */
public class FileServlet extends BaseServlet {

	//处理文件上传的相关函数
	/**
	 * 处理上传的文件
	 * @return 
	 */
	public void Upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = this.getServletContext().getRealPath("/WEB-INF/Upload");
		//上传时生成的临时文件保存目录
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			//创建临时目录
			tmpFile.mkdir();
		}

		//消息提示初始化
		String message = "";
		//创建变量文件拓展名
		String PathAndName = "";
		try{
			//使用Apache文件上传组件处理文件上传步骤：
			//1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			//设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);
			//2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//监听文件上传进度
			upload.setProgressListener(new ProgressListener(){
				public void update(long pBytesRead, long pContentLength, int arg2) {
					//暂时不显示文件上传进度
					//System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
					/**
					 * 文件大小为：14608,当前已处理：4096                      
					 * 文件大小为：14608,当前已处理：7367
					 * 文件大小为：14608,当前已处理：11419 
					 * 文件大小为：14608,当前已处理：14608
					 */
				}
			});
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8"); 
			//3、判断提交上来的数据是否是上传表单的数据
			if(!ServletFileUpload.isMultipartContent(request)){
				//按照传统方式获取数据
				return;
			}

			//设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
			upload.setFileSizeMax(1024*1024);
			//设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
			upload.setSizeMax(1024*1024*10);
			//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				//如果fileitem中封装的是普通输入项的数据
				if(item.isFormField()){
					String name = item.getFieldName();
					//解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					//value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				}else{//如果fileitem中封装的是上传文件
					//得到上传的文件名称，
					String filename = item.getName();
					System.out.println("上传的文件，文件名是"+filename);
					if(filename==null || filename.trim().equals("")){
						continue;
					}
					//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					//得到上传文件的扩展名
					String fileExtName = filename.substring(filename.lastIndexOf(".")+1);

					//如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
					System.out.println("上传的文件的扩展名是："+fileExtName);


					//获取item中的上传文件的输入流
					InputStream in = item.getInputStream();


					//得到文件保存的名称
					//使用配置表id（ConfigId来重命名文件）
					String saveFilename = makeFileName("upload",fileExtName);
					//得到文件的保存目录
					String realSavePath = makePath(savePath);

					//限制为文件的上传类型是xls或者xlsx
					if(    ( (fileExtName.equals("xls")) || (fileExtName.equals("xlsx")) )  ) {
						//测试信息
						System.out.println("您上传的是xls、xlsx文件，文件上传已启动");

						//创建一个文件输出流
						PathAndName = realSavePath + "\\" + saveFilename;
						FileOutputStream out = new FileOutputStream(PathAndName);
						//创建一个缓冲区
						byte buffer[] = new byte[1024];
						//判断输入流中的数据是否已经读完的标识
						int len = 0;
						//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while((len=in.read(buffer))>0){
							//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
							out.write(buffer, 0, len);
						}
						//关闭输入流
						in.close();
						//关闭输出流
						out.close();
						//删除处理文件上传时生成的临时文件
						//item.delete();
						message = "文件上传成功！";
					}else{
						//错误：文件格式不正确  已处理
						message="文件格式不正确，仅支持xls和xlsx两种文件格式";
						System.out.println(message);
						request.setAttribute("message", message);
						request.getRequestDispatcher("/WEB-INF/Pages/File/message.jsp").forward(request, response);
						return;
					}    				

				}
			}
		}catch (FileUploadBase.FileSizeLimitExceededException e) {
			//错误：文件过大    已处理
			e.printStackTrace();
			request.setAttribute("message", "单个文件超出最大值！！！");
			request.getRequestDispatcher("/WEB-INF/Pages/File/message.jsp").forward(request, response);
			return;
		}catch (FileUploadBase.SizeLimitExceededException e) {
			//错误：文件总大小过大  已处理
			e.printStackTrace();
			request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
			request.getRequestDispatcher("/WEB-INF/Pages/File/message.jsp").forward(request, response);
			return;
		}catch (Exception e) {
			//错误：其他错误  已处理
			message= "文件上传失败！";
			e.printStackTrace();
			request.setAttribute("message",message);
			request.getRequestDispatcher("/WEB-INF/Pages/File/message.jsp").forward(request, response);
			return;
		}

		//文件上传成功，转调至POI的Excel解析，将文件中的数据导入数据库

		//将需要的数据写到request中
		request.setAttribute("PathAndName", PathAndName);
		//测试数据
		System.out.println("文件已经成功上传，即将跳转到转跳页面");
		request.getRequestDispatcher("/WEB-INF/Pages/File/ToPOI.jsp").forward(request, response);
	}

	/**
	 * 生成上传文件的文件名
	 * @param fileName  文件名
	 * @param fileExtName 文件拓展名
	 * @return
	 */
	private String makeFileName(String fileName, String fileExtName){  //2.jpg
		//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return fileName+"."+fileExtName;
	}

	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * @Method: makePath
	 * @Description: 
	 * @Anthor:朱晓东
	 *
	 * @param filename 文件名，要根据文件名生成存储目录
	 * @param savePath 文件存储路径
	 * @return 新的存储目录
	 */ 
	private String makePath(String savePath){

		//构造新的保存目录

		String dir = savePath ;

		//测试信息
		System.out.println("文件的上传路径是"+dir);

		//File既可以代表文件也可以代表目录
		File file = new File(dir);

		if(file.exists()){
			//如果目录存在，删除目录（目的是为了清空目录中的文件）
			deleteDir(file);
		}
		//创建目录
		file.mkdirs();
		return dir;
	}



	//功能：删除某个目录及目录下的所有子目录和文件
	//项目模块合并之后，可以放到service层里面

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful.
	 *                 If a deletion fails, the method stops attempting to
	 *                 delete and returns "false".
	 */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			//递归删除目录中的子目录下
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 显示文件下载列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ListFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//测试信息
		System.out.println("FileServlet已经转跳  这里是 ListFile方法");

		//从之前的JSP页面中获取相应的参数
		String ConfigId = (String) request.getSession().getAttribute("ConfigId");;
		String DataType = (String) request.getSession().getAttribute("DataType");;

		//测试信息
		System.out.println("FileServlet已经获取ConfId的信息    ConfigId="+ConfigId);
		System.out.println("FileServlet已经获取DataType的信息    DataType="+DataType);

		//根据DataType获取不同的文件下载路径
		String uploadFilePath=null;
		switch (DataType) {
		case "ParticipatorList":
			uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/Download/ParticipatorList"+"/"+ConfigId);
			//测试信息
			System.out.println(uploadFilePath);
			break;			
		case "OrderWinner":
			uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/Download/ResultList/"+"/"+ConfigId);
			System.out.println(uploadFilePath);
			break;
		case "LotteryWinner":
			uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/Download/ResultList/"+"/"+ConfigId);
			System.out.println(uploadFilePath);
			break;

		default:
			System.out.println("DataType参数错误，其值只能为ParticipatorList和ResultList");
			break;
		}	

		//存储要下载的文件名
		Map<String,String> fileNameMap = new HashMap<String,String>();
		//递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
		listfile(new File(uploadFilePath),fileNameMap);//File既可以代表一个文件也可以代表一个目录

		//测试信息
		//遍历map集合，查看里面的内容
		for(Entry<String, String> entry : fileNameMap.entrySet()) {  
			System.out.println(entry.getKey()+"      "+entry.getValue());  
		}  

		//将Map集合发送到listfile.jsp页面进行显示
		request.setAttribute("fileNameMap", fileNameMap);
		//将数据类型发送到listfile.jsp页面
		request.setAttribute("DataType", DataType);
		//将ConfigId发送到listfile.jsp页面
		request.setAttribute("ConfigId", ConfigId);

		//测试信息
		System.out.println("ListFileServlet一切正常，即将转跳到/WEB-INF/Pages/File/listfile.jsp");
		request.getRequestDispatcher("/WEB-INF/Pages/File/listfile.jsp").forward(request, response);
	}

	/**
	 * @Method: listfile
	 * @Description: 递归遍历指定目录下的所有文件
	 * @Anthor:朱晓东
	 * @param file 即代表一个文件，也代表一个文件目录
	 * @param map 存储文件名的Map集合
	 */ 
	public void listfile(File file,Map<String,String> map){
		//如果file代表的不是一个文件，而是一个目录
		if(!file.isFile()){
			//列出该目录下的所有文件和目录
			File files[] = file.listFiles();
			//遍历files[]数组
			for(File f : files){
				//递归
				listfile(f,map);
			}
		}else{
			//如果file代表的是一个文件的话，就获取它的文件名
			String realName = file.getName();
			//测试信息
			System.out.println("真是文件名称是"+realName);

			//file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
			map.put(file.getName(), realName);
		}
	}


	//文件下载相关
	public void Download(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//测试信息
		System.out.println("DownLoadServlet转跳成功");

		//得到要下载的文件名
		String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
		System.out.println(fileName);
		fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
		//得到下载文件的分类
		String DataType = (String) request.getSession().getAttribute("DataType");
		//获取ConfigId
		String ConfigId = (String) request.getSession().getAttribute("ConfigId");

		//测试信息
		System.out.println("要下载的文件名称是"+fileName);
		System.out.println("文件类别是"+DataType);


		//按照文件类型DataType选择文件的下载路径
		String fileSaveRootPath = null;
		switch (DataType) {
		case "ParticipatorList":
			//ParticipatorList类型的下载数据都保存在  Download/ParticipatorList/抽奖配置id
			fileSaveRootPath=this.getServletContext().getRealPath("WEB-INF/Download/ParticipatorList/"+"/"+ConfigId);
			break;

		case "ResultList":
			////ResultList类型的下载数据都保存在  Download/ResultList/抽奖配置id
			fileSaveRootPath=this.getServletContext().getRealPath("WEB-INF/Download/ResultList/"+"/"+ConfigId);
			break;

		default:
			break;
		}


		//通过文件名找出文件的所在目录
		String path = fileSaveRootPath;
		//得到要下载的文件
		File file = new File(path + "\\" + fileName);
		//测试信息
		System.out.println(file);
		//如果文件不存在
		if(!file.exists()){
			request.setAttribute("message", "您要下载的资源已被删除！！");
			request.getRequestDispatcher("/WEB-INF/Pages/File/listfile.jsp").forward(request, response);
			return;
		}
		//处理文件名
		String realname = fileName.substring(fileName.indexOf("_")+1);
		//设置响应头，控制浏览器下载该文件
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		//读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(path + "\\" + fileName);
		//创建输出流
		OutputStream out = response.getOutputStream();
		//创建缓冲区
		byte buffer[] = new byte[1024];
		int len = 0;
		//循环将输入流中的内容读取到缓冲区当中
		while((len=in.read(buffer))>0){
			//输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
		}
		//关闭文件输入流
		in.close();
		//关闭输出流
		out.close();
	}

}
