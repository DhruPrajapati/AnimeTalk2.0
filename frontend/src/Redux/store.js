import { applyMiddleware, combineReducers, legacy_createStore } from "redux";
import { thunk } from "redux-thunk";
import { authReducer } from "./Reducers/authReducer";
import postReducers from "./Reducers/postReducers";

const rootReducers = combineReducers({
  auth: authReducer,
  post: postReducers,
});

export const store = legacy_createStore(rootReducers,applyMiddleware(thunk))