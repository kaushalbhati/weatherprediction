import React, { Component } from 'react'
import weatherService from '../services/weatherService'

class ViewweatherComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            weather: {}
        }
    }

    componentDidMount(){
        weatherService.getweatherById(this.state.id).then( res => {
            this.setState({weather: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View weather Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> weather First Name: </label>
                            <div> { this.state.weather.firstName }</div>
                        </div>
                        <div className = "row">
                            <label> weather Last Name: </label>
                            <div> { this.state.weather.lastName }</div>
                        </div>
                        <div className = "row">
                            <label> weather Email ID: </label>
                            <div> { this.state.weather.emailId }</div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default ViewweatherComponent
