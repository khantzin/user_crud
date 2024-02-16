package com.kz.bookingsystem.exception;

/**
 * @Author Jameslwin
 * @CreatedAt: Jun 26, 2021
 */
public class FieldError {

	private String fieldCode;

	private String errorMessage;

	public FieldError(String fieldErrorCode, String errorMessage) {
		super();
		this.fieldCode = fieldErrorCode;
		this.errorMessage = errorMessage;
	}

	public enum FieldCode {
		USER_NOT_FOND("U001"),
		USER_PASSWROD_REQUIRED("UP001"),
		USER_NAME_REQUIRED("U002"),
		PASSWORD_REQURIED("P001"),
		WRONG_PASSWORD("P002"),
		RQ_PASSWORD_REQUIRED("P003"),
		PASSWORD_DEACTIVATED("P004"),
		TOKEN_REQUIRED("2033"),
		DYPRODUCT_UPDATE_ERROR("DP001"),
		DYPRODUCT_SEARCH_ERROR("DP002"),
		DYPRODUCT_DELETE_ERROR("DP003"),
		DYNAMIC_TABLE_REQUEST_ERROR("DT001"),
		DYNAMIC_TABLE_CREATE_ERROR("DT002"),
		DYNAMIC_TABLE_UPDATE_ERROR("DT003"),
		DYNAMIC_COLUMN_CREATE_ERROR("DT004"),
		DYNAMIC_COLUMN_UPDATE_ERROR("DT005"),
		DYNAMIC_COLUMN_OPERATION_ERROR("DT006"),
		DYNAMIC_COLUMN_TYPE_ERROR("DT006"),
		DYNAMIC_COLUMN_TYPE_REQUIRED_ERROR("DT007"),
		DYNAMIC_COLUMN_DUPLICATE_ERROR("DT008"),
		DYNAMIC_COLUMN_ID_ERROR("DT009"),
		DYNAMIC_COLUMN_TABLE_ERROR("DT0010"),
		DYNAMIC_COLUMN_SAMENAME_ERROR("DT0011"),
		DYNAMIC_COLUMN_RENAME_EXISTS_ERROR("DT0012"),
		DYNAMIC_COLUMN_REQ_EMPTY_ERROR("DT0013"),
		DYNAMIC_DUMMY_TABLE_CREATE_ERROR("DT0014"),
		PAGEDATA_CREATE_COLUMN_EXIST_ERROR("PD001"),
		PAGEDATA_UPDATE_VALIDATEID_ERROR("PD002"),
		PAGEDATA_TABLE_ERROR("PD003"),
		PAGEDATA_TYPE_ERROR("PD004"),
		PAGEDATA_VALUE_ERROR("PD005"),
		AGENTPRODUCT_REQUIRED_ERROR("PD006");
		
		private String code;

		public String getCode() {
			return code;
		}

		private FieldCode(String code) {
			this.code = code;
		}
	}

	public enum ErrorMessage {
		USER_NOT_FOND("user not found!"),
		USER_PASSWROD_REQUIRED("Invalid Request. username and password required "),
		USER_NAME_REQUIRED("Invalid Request. username is required"),
		PASSWORD_REQURIED("password is requred"),
		WRONG_PASSWORD("Password Incorrect!"),
		RQ_PASSWORD_REQUIRED("Invalid Request. password is required"),
		PASSWORD_DEACTIVATED("Account is deactivated"),
		TOKEN_REQUIRED("token is required"),
		TOKEN_INVALID("Invalid token"),
		INCORRECT_OTP("The OTP you've entered is incorrect. Please try again."),
		EMPTY_OTP("Please enter OTP Code."),
		DYPRODUCT_UPDATE_ERROR("Invalid Id. No record found to update"),
		DYPRODUCT_SEARCH_ERROR("Invalid Id. No record found"),
		DYPRODUCT_DELETE_ERROR("Invalid Id. No record found to delete"),
		DYNAMIC_TABLE_CREATE_ERROR("Table already exist"),
		DYNAMIC_TABLE_UPDATE_ERROR("Invalid old table name"),
		DYNAMIC_TABLE_REQUEST_ERROR("Table Name cannot be empty. Please Provide Table Name"),
		DYNAMIC_COLUMN_CREATE_ERROR("Column already exist"),
		DYNAMIC_COLUMN_UPDATE_ERROR("Column does not exist to update"),
		DYNAMIC_COLUMN_OPERATION_ERROR("Invalid Operation"),
		DYNAMIC_COLUMN_TYPE_ERROR("Invalid data type"),
		DYNAMIC_COLUMN_TYPE_REQUIRED_ERROR("type is required. Please provide data type"),
		DYNAMIC_COLUMN_DUPLICATE_ERROR("Duplicate column request"),
		DYNAMIC_COLUMN_ID_ERROR("Invalid request! Column id is not allowed to update"),
		DYNAMIC_COLUMN_TABLE_ERROR("Invalid Table"),
		DYNAMIC_COLUMN_SAMENAME_ERROR("name and oldColName cannot be the same"),
		DYNAMIC_COLUMN_RENAME_EXISTS_ERROR("Cannot rename the column. Column name already exists"),
		DYNAMIC_COLUMN_REQ_EMPTY_ERROR("Please provide required info to update column"),
		DYNAMIC_DUMMY_TABLE_CREATE_ERROR("dummy table name already exists. Please change requested table name"),
		PAGEDATA_CREATE_COLUMN_EXIST_ERROR("Invalid Column"),
		PAGEDATA_UPDATE_VALIDATEID_ERROR("Invalid resourceId, pageId or refId"),
		PAGEDATA_TABLE_ERROR("Invalid Table"),
		PAGEDATA_TYPE_ERROR("Invalid Type"),
		PAGEDATA_VALUE_ERROR("Invalid Value"),
		AGENTPRODUCT_REQUIRED_ERROR("Product Id and agent Id are required");
		private String message;

		public String getMessage() {
			return message;
		}

		private ErrorMessage(String message) {
			this.message = message;
		}
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
