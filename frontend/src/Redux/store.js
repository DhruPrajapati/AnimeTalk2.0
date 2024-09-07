import { applyMiddleware, combineReducers, legacy_createStore } from "redux";
import { thunk } from "redux-thunk";
import { authReducer } from "./Reducers/authReducer";
import postReducers from "./Reducers/postReducers";
import { messageReducer } from "./Reducers/messageReducer";

const rootReducers = combineReducers({
  auth: authReducer,
  post: postReducers,
  message: messageReducer,
});

export const store = legacy_createStore(rootReducers,applyMiddleware(thunk))