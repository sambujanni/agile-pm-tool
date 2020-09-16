import React, { Component } from "react";
import Projects from "./project/Projects";
import CreateProjectButton from "./project/CreateProjectButton";

class Dashboard extends Component {
  render() {
    return (
      <div>
        <div className="projects">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <h1 className="display-4 text-center">Projects</h1>
                <br />
                <CreateProjectButton />
                <br />
                <hr />
              </div>
            </div>
          </div>
        </div>
        <Projects />
      </div>
    );
  }
}
export default Dashboard;
