import { Button, TextField } from "@mui/material";
import { ErrorMessage, Field, Form, Formik } from "formik";
import React, { Fragment, useState } from "react";
import { useDispatch } from "react-redux";
import * as Yup from "yup";
import { loginUserAction } from "../../Redux/Actions/authAction";

const Login = () => {
  const [formValue, setFormValue] = useState();
  const dispatch = useDispatch()

  const initialValues = { email: "", password: "" };

  const validationSchema = {
    email: Yup.string().email("Invalid Email").required("Email Required"),
    password: Yup.string()
      .min(6, "Password must be atleast 6 characters")
      .required("Password Required"),
  };

  const handleSubmit = (values) => {
    console.log("handle submit",values);
    dispatch(loginUserAction({data:values}))

  };
  return (
    <Fragment>
      <Formik
        onSubmit={handleSubmit}
        // validationSchema={validationSchema}
        initialValues={initialValues}>
        <Form className="space-y-5">
          <div className="space-y-5">
            <div>
              <Field
                as={TextField}
                name="email"
                placeholder="Email"
                type="email"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage
                name="email"
                component="div"
                className="text-red-500"
              />
            </div>
            <div>
              <Field
                as={TextField}
                name="password"
                placeholder="password"
                type="password"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage
                name="password"
                component="div"
                className="text-red-500"
              />
            </div>
            <Button
              sx={{ padding: ".8rem 0rem" }}
              fullWidth 
              type="submit"
              variant="contained"
              color="primary">
              Login
            </Button>
          </div>
        </Form>
      </Formik>
    </Fragment>
  );
};

export default Login;
