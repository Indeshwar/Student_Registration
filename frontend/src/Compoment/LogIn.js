import React from "react";
import "./Login.css";
import { Link } from "react-router-dom";
import Footer from "./Footer";
const LogIn = () => {
  return (
    <>
      <div className="container-fluid">
        <div className="row">
          <div className="col-12 col-sm-8 col-md-4 col-lg-4 mt-5">
          </div>
          <div className="col-12 col-sm-8 col-md-8 col-lg-3 mt-5">
            <div className="main-content">
              <form className="bg-primary-subtle rounded-2  p-4">
                <h1 className="h1f">Student LogIn</h1>
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
                    Log In
                  </button>
                </div>

                <h2 className="h2f">Forgot password</h2>
              </form>
            </div>
          </div>
        </div>
        {/* row closing */}
        <div className="row mt-2">
          <div className="col-12 col-sm-8 col-md-4 col-lg-4"></div>

          <div className="col-12 col-sm-8 col-md-8 col-lg-3">
            <div className="main-content-2">
              <div className="bg-primary-subtle rounded-2 p-4">
                Don't Have an account?
                <Link to="/register" className="h2f">
                  Sign up
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

export default LogIn;
