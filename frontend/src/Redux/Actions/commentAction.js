import {
  CREATE_COMMENT_FAILURE,
  CREATE_COMMENT_REQUEST,
  CREATE_COMMENT_SUCCESS,
} from "../Constant/commentConstant";

export const createCommentAction = (reqData) => async (dispatch) => {
  dispatch({
    type: CREATE_COMMENT_REQUEST,
  });
  try {
    const { data } = await api.post(
      `/api/comments/${reqData.posdId}`,
      reqData.data
    );
    dispatch({ type: CREATE_COMMENT_SUCCESS, payload: data });
    console.log("created COMMENT", data);
  } catch (error) {
    console.log("error", error);
    dispatch({ type: CREATE_COMMENT_FAILURE, payload: error });
  }
};
