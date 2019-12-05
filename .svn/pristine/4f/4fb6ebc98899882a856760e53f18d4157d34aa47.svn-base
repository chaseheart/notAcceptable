///**
// * 根据页面规则和数据造table
// * 
// * @param tableName
// * @param jsonArr
// * @param dataList
// * @returns
// */
//function createTable(tableName, jsonArr, dataList) {
//	$("#" + tableName + " tbody tr").remove();
//	for ( var i in dataList) {
//		var tr = '';
//		var trData = dataList[i];
//		for (var j = 0; j < jsonArr.length; j++) {
//			if (jsonArr[j].type == "Checkbox") {
//				var field = jsonArr[j].name;
//				if (jsonArr[j].functionName == "hiddenCheckbox") {
//					tr += '<tr id="' + trData[field] + '">';
//				}else{
//					tr += '<tr id="'
//						+ trData[field]
//						+ '">'
//						+ '<td><input type="checkbox" class="ace"/><span class="lbl"></span></td>';
//				}
//				continue;
//			}
//			if (jsonArr[j].type == "String") {
//				var field = jsonArr[j].name;
//				var newField;
//				if (jsonArr[j].functionName == "emptyToNull") {
//					var getFuntion = eval(jsonArr[j].functionName);
//					newField = getFuntion(trData[field]);
//				} else if (jsonArr[j].functionName == "changeToCHStatus") {
//					var getFuntion = eval(jsonArr[j].functionName);
//					newField = getFuntion(trData[field]);
//				} else if (jsonArr[j].functionName != null) {
//					var getFuntion = eval(jsonArr[j].functionName);
//					newField = getFuntion(trData[field]);
//				} else if (jsonArr[j].name == "option"){
//					newField = trData[field].split(',')[0];
//				} 
//				// 会签 ycs 190530 start
//				else if (jsonArr[j].name == "stepName"){
//					if(trData["countersignState"] == "N"){
//						newField = "会签";
//					// 转项 ccy 20190614 start
//					}else if(trData["preAssignId"] != "" && trData["preStepId"] == "" && trData["userName"] != window.top.$("#loginUser").html()){
//						newField = "转项";
//						// 转项 ccy 20190614 end
//					}else{
//						newField = trData[field];
//					}
//				}
//				// 会签 ycs 190530 end
//				
//				else {
//					newField = trData[field];
//					if(newField == null ){
//						newField = '';
//					}
//				}
//				tr += '<td title="' + newField + '">' + newField + '</td>';
//				continue;
//			}
//			if (jsonArr[j].type == "timeDiff") {
//				var field1 = jsonArr[j].name[0];
//				var field2 = jsonArr[j].name[1];
//				var getFuntion = eval(jsonArr[j].functionName);
//				newField = getFuntion(trData[field1], trData[field2]);
//
//				tr += '<td title="' + newField + '">' + newField + '</td>';
//				continue;
//			}
//			if (jsonArr[j].type == "Date") {
//				var field1 = jsonArr[j].name[0];
//				var field2 = jsonArr[j].name[1];
//				var dateData;
//				if (jsonArr[j].functionName == "formatDate") {
//					var getFuntion = eval(jsonArr[j].functionName);
//					var date1 = getFuntion(trData[field1]);
//					var date2 = getFuntion(trData[field2]);
//					dateData = date1 + '~' + date2;
//				}
//				tr += '<td title="' + dateData + '">' + dateData + '</td>';
//				continue;
//			}
//			if (jsonArr[j].type == 'delay') {
//				var field1 = trData[jsonArr[j].name[0]];
//				var field2 = trData[jsonArr[j].name[1]];
//				var dateData = delayTime(field1, field2);
//				tr += '<td title="' + dateData + '">' + dateData + '</td>';
//				continue;
//			}
//			if (jsonArr[j].type == "Time") {
//				var field = jsonArr[j].name;
//				var dateData;
//				if (jsonArr[j].functionName == "formatTime") {
//					var getFuntion = eval(jsonArr[j].functionName);
//					var date = getFuntion(new Date(trData[field]));
//					dateData = date;
//				}
//				tr += '<td title="' + dateData + '">' + dateData + '</td>';
//				continue;
//			}
//
//			if (jsonArr[j].type == "a") {
//				var field1 = jsonArr[j].name;
//				tr += '<td><a href="#">' + trData[field1] + '</a></td>';
//				continue;
//			}
//			if (jsonArr[j].type == "a-button") {
//				var field1 = jsonArr[j].name[0];
//				var field2 = jsonArr[j].name[1];
//				tr += '<td><a class="' + field1
//						+ '" href="#">修改</a>&nbsp;&nbsp;<a class="' + field2
//						+ '" href="#">删除</a></td>';
//				continue;
//			}
//			if (jsonArr[j].type == "a-button2") {
//				var field1 = jsonArr[j].name[0];
//				var field2 = jsonArr[j].name[1];
//				var url = trData[jsonArr[j].name[2]];
//				tr += '<td><a download="" target="_blank" class="' + field1
//						+ '" href="' + url + '">下载</a>&nbsp;&nbsp;<a class="'
//						+ field2 + '" href="#">删除</a></td>';
//				continue;
//			}
//			if (jsonArr[j].type == "a-button3") {
//				var field1 = jsonArr[j].name[0];
//				var field2 = trData[jsonArr[j].name[1]];
//				tr += '<td><a id="' + field2 + '" class="' + field1
//						+ '" href="#">读取模板</a></td>';
//				continue;
//			}
//			// 模板删除 20190514 lzx start
//			if (jsonArr[j].type == "a-button4") {
//				var field1 = jsonArr[j].name[0];
//				var field2 = trData[jsonArr[j].name[1]];
//				var field3 = jsonArr[j].name[2];
//				tr += '<td><a id="' + field2 + '" class="' + field1
//						+ '" href="#">读取模板</a>' + '<a id="' + field2
//						+ '" class="' + field3 + '" href="#">删除模板</a></td>';
//				continue;
//			}
//			// 模板删除 20190514 lzx end
//			if (jsonArr[j].type == "hidden") {
//				var fieldClass = jsonArr[j].name[0];
//				var field = jsonArr[j].name[1];
//				tr += '<td style="display: none" class="' + fieldClass + '">'
//						+ trData[field] + '</td>';
//				continue;
//			}
//			if (jsonArr[j].type == "No") {
//				if (j == 0) {
//					tr += '<tr><td>' + (parseInt(i) + 1) + '</td>';
//				} else {
//					tr += '<td> ' + (parseInt(i) + 1) + '</td>';
//				}
//			}
//		}
//		tr += '</tr>';
//		$('#' + tableName + ' tbody').append(tr);
//	}
//}

// /**
// * 根据json规则和数据造form
// *
// * @param tableName
// * @param jsonArr
// * @param dataList
// * @returns
// */
// function createForm(tableName, jsonArr, dataList,titleNum) {
// $("#" + tableName + " tbody tr").remove();
// var tr = '<tr>';
// for ( var i in dataList) {
// var trData = dataList[i];
// for (var j = 0; j < jsonArr.length; j++) {
// if (jsonArr[j].type == "String") {
// var field = jsonArr[j].name;
// var newField;
// if (jsonArr[j].functionName == "changeToCHMust") {
// var getFuntion = eval(jsonArr[j].functionName);
// newField = getFuntion(trData[field]);
// } else {
// newField = trData[field];
// }
// tr += '<td title="' + newField + '">' + newField + '</td>';
// continue;
// }
// if (jsonArr[j].type == "hidden") {
// var fieldClass = jsonArr[j].name[0];
// var field = jsonArr[j].name[1];
// tr += '<td style="display: none" class="' + fieldClass + '">'
// + trData[field] + '</td>';
// continue;
// }
// }
// if (i == dataList.length - 1 && (Number(i)+1) % titleNum != 0) {
// for(var m = 0;m<(titleNum-(Number(i)+1) % titleNum);m++){
// for (var j = 0; j < jsonArr.length; j++) {
// if (jsonArr[j].type != "hidden") {
// tr += '<td></td>';
// }
// }
// }
// tr += '</tr>';
// } else if ((Number(i)+1)% titleNum == 0) {
// tr += '</tr><tr>';
// }
// }
// $('#' + tableName + ' tbody').append(tr);
// }

///**
// * 根据json规则和数据造form
// * 
// * @param tableName
// * @param jsonArr
// * @param dataList
// * @returns
// */
//function createForm(tableName, jsonArr, dataList, titleNum, optionSen) {
//	$("#" + tableName + " tbody tr").remove();
//	var preX = 0;
//	var preY = 0;
//	var maxLineNum = Number(dataList[dataList.length - 1].fieldLocation
//			.split(",")[0]) + 1;
//	var maxColNum = Number(titleNum);
//	var tr = '';
//	for ( var i in dataList) {
//		var trData = dataList[i];
//		var lineNum = trData.fieldLocation.split(",")[0];
//		var colNum = trData.fieldLocation.split(",")[1];
//		for (var x = preX; x < maxLineNum; x++) {
//			if (preY == 0) {
//				tr += '<tr>';
//			}
//			for (var y = preY; y < maxColNum; y++) {
//				if (x == lineNum && y == colNum) {
//					tr = writeData(tr, trData, optionSen);
//					// console.log(trData);
//					if (colNum == maxColNum - 1) {
//						preY = 0;
//						preX = Number(lineNum) + 1;
//						tr += '</tr>';
//					} else {
//						preY = Number(colNum) + 1;
//						preX = Number(lineNum);
//					}
//					if (i == (dataList.length - 1) && colNum != (maxColNum - 1)) {
//						continue;
//					} else {
//						x = maxLineNum;
//						y = maxColNum;
//					}
//
//				} else {
//					var emptyJson = {};
//					tr = writeData(tr, emptyJson, optionSen);
//					if (y == maxColNum - 1) {
//						tr += '</tr>';
//						preY = 0;
//						continue;
//					}
//				}
//			}
//		}
//	}
//	$('#' + tableName + ' tbody').append(tr);
//	//隐藏合并单元格之后的多余格子
//	jsonLen = jsonArr.length;
//	$('#' + tableName + " tbody tr td").each(function() {
//						if ($(this).hasClass("l")) {
//							for (var i = 4; i < $(this).parent().children("td").length; i++) {
//								if ($(this).parent().children("td").eq(i).css("display") != "none") {
//									$(this).parent().children("td").eq(i).addClass("hidden-td");
//								}
//							}
//						} else if ($(this).hasClass("m")) {
//							var index = $(this).parent().children("td").index(
//									this);
//
//							// 隐藏当前格子的后两格
//							$(this).parent().children("td").eq(index + jsonLen)
//									.addClass("hidden-td");
//						}
//					});
//}
//
//function writeData(tr, trData, optionSen) {
//	if (JSON.stringify(trData) != "{}") {
//		for (var j = 0; j < jsonArr.length; j++) {
//			var size = trData.fieldLocation.split(",")[2];
//			if (jsonArr[j].type == "String") {
//				var field = jsonArr[j].name;
//				var newField;
//				if (jsonArr[j].functionName == "changeToCHMust") {
//					var getFuntion = eval(jsonArr[j].functionName);
//					newField = getFuntion(trData[field]);
//				} else {
//					newField = trData[field];
//				}
//				if (size == "l") {
//					if(jsonArr[j].functionName == "changeToCHMust"){
//						tr += '<td colspan="10" class="l" title="' + newField + '">' + newField
//						+ '</td>';
//					}else{
//						tr += '<td colspan="1" class="l" title="' + newField + '">' + newField
//						+ '</td>';
//					}
//				} else if (size == "m") {
//					if(jsonArr[j].functionName == "changeToCHMust"){
//						tr += '<td colspan="4" class="m" title="' + newField + '">' + newField
//						+ '</td>';
//					}else{
//						tr += '<td colspan="1" class="m" title="' + newField + '">' + newField
//						+ '</td>';
//					}
//				} else {
//					tr += '<td title="' + newField + '">' + newField + '</td>';
//				}
//				continue;
//			}
//			if (jsonArr[j].type == "input") {
//				var field = jsonArr[j].name;
//				var newField;
//				if (jsonArr[j].functionName == "changeToCHMust") {
//					var getFuntion = eval(jsonArr[j].functionName);
//					newField = getFuntion(trData[field]);
//				} else {
//					newField = trData[field];
//				}
//				if (size == "l") {
//					tr += '<td colspan="7" class="l">' + newField
//							+ '</td>';
//				} else if (size == "m") {
//					tr += '<td colspan="3" class="m">' + newField
//							+ '</td>';
//				} else {
//					tr += '<td>' + newField + '</td>';
//				}
//				continue;
//			}
//
//			if (trData["isRead"] == "1") {
//				if (jsonArr[j].type == "textarea") {// TODO
//					var field = jsonArr[j].name;
//					if (size == "l") {
//						tr += '<td colspan="1" class="l"><textarea type="text" id="'
//								+ trData['id']
//								+ '" value="'
//								+ '"  disabled="disabled" oninput="changeContent(this)" class="extended" rows="1" >'
//								+ trData[field] + '</textarea></td>';
//					} else if (size == "m") {
//						tr += '<td colspan="1" class="m"><textarea type="text" id="'
//								+ trData['id']
//								+ '" value="'
//								+ '"  disabled="disabled" oninput="changeContent(this)" class="extended" rows="1" >'
//								+ trData[field] + '</textarea></td>';
//					} else {
//						tr += '<td><textarea type="text" id="'
//								+ trData['id']
//								+ '" value="'
//								+ '"  disabled="disabled" oninput="changeContent(this)" class="extended" rows="1" >'
//								+ trData[field] + '</textarea></td>';
//					}
//					continue;
//				}
//
//				if (jsonArr[j].type == "select") {
//					var field = jsonArr[j].name;
//					OptionSen = optionSen.replace(trData[field] + "'",
//							trData[field] + "' selected");
//					optionSen = optionSen.replace("fieldType'>",
//							"fieldType' disabled='disabled' >");
//					if (size == "l") {
//						tr += '<td colspan="7" class="l">' + optionSen
//								+ '</td>';
//					} else if (size == "m") {
//						tr += '<td colspan="3" class="m">' + optionSen
//								+ '</td>';
//					} else {
//						tr += '<td>' + optionSen + '</td>';
//					}
//					continue;
//				}
//
//			} /*
//				 * else if (trData["isRead"] == "2") { if (jsonArr[j].type ==
//				 * "input") { var field = jsonArr[j].name; tr += '<td><input
//				 * type="text" value="' + trData[field] + '" disabled="disabled"
//				 * class="solid" /></td>'; continue; }
//				 * 
//				 * if (jsonArr[j].type == "select") { var field =
//				 * jsonArr[j].name; optionSen = optionSen.replace(trData[field] +
//				 * "'", trData[field] + "' selected"); optionSen =
//				 * optionSen.replace("fieldType'>", "fieldType'
//				 * disabled='disabled' >"); tr += '<td>' + optionSen + '</td>';
//				 * continue; } }
//				 */else {
//				if (jsonArr[j].type == "textarea") {
//					var field = jsonArr[j].name;
//					if (size == "l") {
//						tr += '<td colspan="1" class="l"><textarea oninput="changeContent(this)" type="text" rows="1">'
//								+ trData[field] + '</textarea></td>';
//					} else if (size == "m") {
//						tr += '<td colspan="1" class="m"><textarea oninput="changeContent(this)" type="text" rows="1">'
//								+ trData[field] + '</textarea></td>';
//					} else {
//						tr += '<td><textarea oninput="changeContent(this)" type="text" rows="1">'
//								+ trData[field] + '</textarea></td>';
//					}
//					continue;
//				}
//
//				if (jsonArr[j].type == "select") {
//					var field = jsonArr[j].name;
//					optionSen = optionSen.replace(trData[field] + "'",
//							trData[field] + "' selected");
//					if (size == "l") {
//						tr += '<td colspan="7" class="l">' + optionSen
//								+ '</td>';
//					} else if (size == "m") {
//						tr += '<td colspan="3" class="m">' + optionSen
//								+ '</td>';
//					} else {
//						tr += '<td>' + optionSen + '</td>';
//					}
//					continue;
//				}
//			}
//
//			if (jsonArr[j].type == "hidden") {
//				var fieldClass = jsonArr[j].name[0];
//				var field = jsonArr[j].name[1];
//				tr += '<td style="display: none" class="' + fieldClass + '">'
//						+ trData[field] + '</td>';
//				continue;
//			}
//		}
//	} else {
//		for (var j = 0; j < jsonArr.length; j++) {
//			if (jsonArr[j].type == "hidden") {
//				tr += '<td style="display: none"></td>';
//				continue;
//			} else {
//				tr += '<td></td>';
//				continue;
//			}
//		}
//	}
//	return tr;
//}

/**
 * 根据页面规则和数据造有序号的table
 * @param tableName table名
 * @param jsonArr table格式
 * @param dataList 数据
 * @param pageNum 当前页数
 * @param lineNum 页面条数
 * @returns
 */
function createTableWithNo(tableName, jsonArr, dataList, pageNum, lineNum) {
	$("#" + tableName + " tbody tr").remove();
	for ( var i in dataList) {
		var tr = '';
		var trData = dataList[i];
		for (var j = 0; j < jsonArr.length; j++) {
			if (jsonArr[j].type == "Checkbox") {
				var field = jsonArr[j].name;
				tr += '<tr id="'
						+ trData[field]
						+ '">'
						+ '<td><label class="ant-checkbox-wrapper"><span class="ant-checkbox"><input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span></span></label></td>';
				continue;
			}
			if (jsonArr[j].type == "String") {
				var field = jsonArr[j].name;
				var newField;
				if (jsonArr[j].functionName == "emptyToNull") {
					var getFuntion = eval(jsonArr[j].functionName);
					newField = getFuntion(trData[field]);
				} else if (jsonArr[j].functionName == "changeToCHStatus") {
					var getFuntion = eval(jsonArr[j].functionName);
					newField = getFuntion(trData[field]);
				} else if (jsonArr[j].functionName != null) {
					var getFuntion = eval(jsonArr[j].functionName);
					newField = getFuntion(trData[field]);
				} else {
					newField = trData[field];
				}
				tr += '<td title="' + newField + '">' + newField + '</td>';
				continue;
			}
			//20190806 dong start
			if (jsonArr[j].type == "timeDiff") {
				var field1 = jsonArr[j].name[0];
				var field2 = jsonArr[j].name[1];
				var getFuntion = eval(jsonArr[j].functionName);
				newField = getFuntion(trData[field1], trData[field2]);

				tr += '<td title="' + newField + '">' + newField + '</td>';
				continue;
			}
			//20190806 dong end
			if (jsonArr[j].type == "Date") {
				var field1 = jsonArr[j].name[0];
				var field2 = jsonArr[j].name[1];
				var dateData;
				if (jsonArr[j].functionName == "formatDate") {
					var getFuntion = eval(jsonArr[j].functionName);
					var date1 = getFuntion(new Date(trData[field1]));
					var date2 = getFuntion(new Date(trData[field2]));
					dateData = date1 + '~' + date2;
				}
				tr += '<td title="' + dateData + '">' + dateData + '</td>';
				continue;
			}
			if (jsonArr[j].type == "Time") {
				var field = jsonArr[j].name;
				var dateData;
				if (jsonArr[j].functionName == "formatTime") {
					var getFuntion = eval(jsonArr[j].functionName);
					var date = getFuntion(new Date(trData[field]));
					dateData = date;
				}
				tr += '<td title="' + dateData + '">' + dateData + '</td>';
				continue;
			}

			if (jsonArr[j].type == "a") {
				var field1 = jsonArr[j].name;
				var newField;
				var hrefUrl = '#';
				if (jsonArr[j].name == "infoType"){
						if(trData[field1] == "待办审批"){
							newField = "处理";
							hrefUrl='/oa010012?type=deal&id='+trData['id']
						}else{
							newField = "查看";
							hrefUrl='/oa010012?type=view&id='+trData['id']
						}
				} else {
					newField = trData[field1];
				}
				tr += '<td><a href="'+ hrefUrl +'">' + newField + '</a></td>';
				continue;
			}
			if (jsonArr[j].type == "a-button") {
				var field1 = jsonArr[j].name[0];
				var field2 = jsonArr[j].name[1];
				tr += '<td><a class="' + field1
						+ '" href="#">修改</a>&nbsp;&nbsp;<a class="' + field2
						+ '" href="#">删除</a></td>';
				continue;
			}
			if (jsonArr[j].type == "a-button4") {
				var fieldArray = trData[jsonArr[j].name].split(',');
				tr += '<td><a href="#" class="' + fieldArray[1] + '">'
						+ fieldArray[0] + '</a></td>';
				continue;
			}
			if (jsonArr[j].type == "a-button-three") {
				var field1 = jsonArr[j].name[0];
				var field2 = jsonArr[j].name[1];
				var field3 = jsonArr[j].name[2];
				tr += '<td><button class="' + field3
						+ '">查看</button>&nbsp;&nbsp;<button class="' + field1
						+ '">修改</button>&nbsp;&nbsp;<button class="' + field2
						+ '">删除</button></td>';
				continue;
			}
			if (jsonArr[j].type == "hidden") {
				var fieldClass = jsonArr[j].name[0];
				var field = jsonArr[j].name[1];
				tr += '<td style="display: none" class="' + fieldClass + '">'
						+ trData[field] + '</td>';
				continue;
			}
			// 使用页面id
			if (jsonArr[j].type == "No") {
				tr += '<td> ' + (parseInt(i) + 1 + (pageNum - 1) * lineNum)
						+ '</td>';
			}
		}
		tr += '</tr>';
		$('#' + tableName + ' tbody').append(tr);
	}
}

/**
 * 空值转'无'
 * 
 * @param data
 * @returns
 */
function emptyToNull(data) {
	if (data == "") {
		data = "无";
	}
	return data;
}

function changeToCHStatus(data) {
	if (data == "Y") {
		data = "在职";
	} else {
		data = "离职";
	}
	return data;
}

/**
 * 是否必填
 * 
 * @param data
 * @returns
 */
function changeToCHMust(data) {
	if (data == "Y") {
		data = "必填";
	} else {
		data = "选填";
	}
	return data;
}

/**
 * 计算到现在的时间段
 * 
 * @param updateTime
 *            比较时间
 * @returns 时间段
 */
function waitingTimeFormat1(updateTime) {
	var nowDateTime = Date.parse(new Date());
	updateTime = updateTime.replace(/-/g,"/");
	var pastDateTime = Date.parse(new Date(updateTime));
	// 天数
	var time = nowDateTime - pastDateTime;
	var days = Math.floor(time / (24 * 3600 * 1000));
	// 小时
	var time1 = time % (24 * 3600 * 1000);
	var hours = Math.floor(time1 / (3600 * 1000));
	// 分
	var time2 = time1 % (3600 * 1000);
	var minutes = Math.floor(time2 / (60 * 1000));

	return days + '天 ' + hours + '时 ' + minutes + '分';
}

/**
 * 创建和修改时间差
 * 
 * @param createTime
 * @param updateTime
 * 
 * @returns
 */
function waitingTimeFormat2(createTime, updateTime) {
	createTime = createTime.replace(/-/g,"/");
	updateTime = updateTime.replace(/-/g,"/");
	var nowDateTime = Date.parse(new Date(updateTime));
	var pastDateTime = Date.parse(new Date(createTime));
	// 天数
	var time = nowDateTime - pastDateTime;
	var days = Math.floor(time / (24 * 3600 * 1000));
	// 小时
	var time1 = time % (24 * 3600 * 1000);
	var hours = Math.floor(time1 / (3600 * 1000));
	// 分
	var time2 = time1 % (3600 * 1000);
	var minutes = Math.floor(time2 / (60 * 1000));
	if (time < 0) {
		days = 0;
		hours = 0;
		minutes = 0;
	}

	return days + '天 ' + hours + '时 ' + minutes + '分';
}

/**
 * 计算延期时间
 * 
 * @param time1
 * @param presetTime
 * @returns
 */
function delayTime(time1, presetTime) {
	time1 = time1.replace(/-/g,"/");
	presetTime = presetTime.replace(/-/g,"/");
	time = Date.parse(new Date(time1)) - Date.parse(new Date(presetTime));
	if (time < 0) {
		return '00:00:00';
	}
	// 小时
	var hours = Math.floor(time / (3600 * 1000));
	// 分
	var timeLeft = time % (3600 * 1000);
	var minutes = Math.floor(timeLeft / (60 * 1000));
	// 秒
	var timeLeft2 = timeLeft % (60 * 1000);
	var second = Math.floor(timeLeft2 / 1000);

	return df(hours) + ':' + df(minutes) + ':' + df(second);
}
// datetime fomate add '0'
function df(t) {
	if (t < 10) {
		return '0' + t;
	} else {
		return t;
	}
}
