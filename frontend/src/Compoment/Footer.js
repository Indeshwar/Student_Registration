import React from 'react'

const Footer = () => {
  return (
    <footer>
        <div className="container-fluid">
          <div className="row">
            <div className="col-6 col-sm-6 col-md-4 col-lg-4 m-auto">
              <div className="single-box">
                <h2>Non Profit Organization</h2>
                <ul>
                    <li>Chicagoland Tharu Society</li>
                    <li>Chicagoland Nepali Society</li>
                    <li>Chicago Nepali Terai Society</li>
                    <li>Nepalese Aid</li>
                </ul>
              </div>
            </div>

            <div className="col-6 col-sm-6 col-md-4 col-lg-4 m-auto">
              <div className="single-box">
                <h2>Universities</h2>
                <ul>
                    <li>Northeastern Illinois University</li>
                    <li>University of Illinois Chicago</li>
                    <li>Illinois Institute of Technology</li>
                    <li>Loyala University</li>
                </ul>
              </div>
            </div>

            <div className="col-6 col-sm-6 col-md-4 col-lg-4 m-auto">
              <div className="single-box">
                <h2>Social Media</h2>
                <ul>
                    <li>Facebook</li>
                    <li>Instagram</li>
                    <li>Twitter</li>
                    <li>YouTube</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </footer>
  )
}

export default Footer