import React from "react";
import "./Login.css";

const LogIn = () => {
  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-12 col-sm-8 col-md-4 col-lg-4 mt-5">
          <h2 className="h2x">This is my demo LogIn Page </h2>
        </div>
        <div className="col-12 col-sm-8 col-md-8 col-lg-3 mt-5 bg-primary-subtle rounded-2 pt-4 pd-3">
          <form>
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
      {/* row closing */}
      <div className="row mt-2">
        <div className="col-12 col-sm-8 col-md-4 col-lg-4"></div>
        <div className="col-12 col-sm-8 col-md-8 col-lg-3 bg-primary-subtle rounded-2">
          <h1 className="h2f">Don't Have an account? Singn up</h1>
        </div>
      </div>
    </div> //container-fluid closing
  );
};

export default LogIn;
