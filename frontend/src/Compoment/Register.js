import React from "react";
import { Link } from "react-router-dom";
import Footer from "./Footer";
const Register = () => {
  return (
    <>
      <div className="container-fluid">
        <div className="row">
          <div className="col-12 col-sm-8 col-md-8 col-lg-3 m-auto">
            <div className="main-content-for-reg">
              <form className="bg-primary-subtle rounded-2 p-4">
                <h1 className="h1f">Student Register</h1>
                <div className="m-4">
                  <input className="form-control" placeholder="Email" />
                </div>
                <div className="m-4">
                  <input className="form-control" placeholder="Full Name" />
                </div>
                <div className="m-4">
                  <input className="form-control" placeholder="UserName" />
                </div>
                <div className="m-4">
                  <input
                    type="password"
                    className="form-control"
                    placeholder="Password"
                  />
                </div>
                <div className="m-4">
                  <button type="submit" className="btn btn-primary">
                    Sign Up
                  </button>
                </div>

                <h2 className="h2f">Forgot password</h2>
              </form>
            </div>
          </div>
        </div>
        <div className="row mt-2">
          <div className="col-12 col-sm-8 col-md-8 col-lg-3 m-auto">
            <div className="main-content-2">
              <div className="bg-primary-subtle rounded-2 p-4 m-auto">
                Have an account?
                <Link to="/" className="h2f">
                  Sign in
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Register;
