import React, { Component } from 'react'
import weatherService from '../services/weatherService';

class CreateweatherComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            firstName: '',
            lastName: '',
            emailId: ''
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.saveOrUpdateweather = this.saveOrUpdateweather.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            weatherService.getweatherById(this.state.id).then( (res) =>{
                let weather = res.data;
                this.setState({firstName: weather.firstName,
                    lastName: weather.lastName,
                    emailId : weather.emailId
                });
            });
        }        
    }
    saveOrUpdateweather = (e) => {
        e.preventDefault();
        let weather = {firstName: this.state.firstName, lastName: this.state.lastName, emailId: this.state.emailId};
        console.log('weather => ' + JSON.stringify(weather));

        // step 5
        if(this.state.id === '_add'){
            weatherService.createweather(weather).then(res =>{
                this.props.history.push('/weathers');
            });
        }else{
            weatherService.updateweather(weather, this.state.id).then( res => {
                this.props.history.push('/weathers');
            });
        }
    }
    
    changeFirstNameHandler= (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler= (event) => {
        this.setState({lastName: event.target.value});
    }

    changeEmailHandler= (event) => {
        this.setState({emailId: event.target.value});
    }

    cancel(){
        this.props.history.push('/weathers');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add weather</h3>
        }else{
            return <h3 className="text-center">Update weather</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> First Name: </label>
                                            <input placeholder="First Name" name="firstName" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Last Name: </label>
                                            <input placeholder="Last Name" name="lastName" className="form-control" 
                                                value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Email Id: </label>
                                            <input placeholder="Email Address" name="emailId" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveOrUpdateweather}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default CreateweatherComponent
