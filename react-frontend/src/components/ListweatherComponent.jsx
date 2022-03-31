import React, { Component } from 'react'
import weatherService from '../services/weatherService'

class ListweatherComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                weathers: []
        }
        this.addweather = this.addweather.bind(this);
        this.editweather = this.editweather.bind(this);
        this.deleteweather = this.deleteweather.bind(this);

        this.publish = this.publish.bind(this);
    }

    publish(topicBox) {
        //alert(this.refs.topic.value);
        const currentcityname = this.refs.topic.value;
        weatherService.getWeatherByCity(currentcityname).then((res) => {
            console.log(res.data);
            this.setState({ weathers: res.data});
        });
      }

    deleteweather(id){
        weatherService.deleteweather(id).then( res => {
            this.setState({weathers: this.state.weathers.filter(weather => weather.id !== id)});
        });
    }
    viewweather(id){
        this.props.history.push(`/view-weather/${id}`);
    }
    editweather(id){
        this.props.history.push(`/add-weather/${id}`);
    }

    componentDidMount(){
        weatherService.getWeatherByCity(this.refs.topic.value).then((res) => {
            console.log(res.data);
            this.setState({ weathers: res.data});
        });
    }

    addweather(){
        this.props.history.push('/add-weather/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">Weather Prediction</h2>
                 <div className = "row">
                 <input required ref="topic" type="text" name="topicBox" placeholder="Enter city here..."/>
                 &nbsp;&nbsp;&nbsp;
                 <button value="Send" onClick={this.publish}>Get Weather</button>
                    {/*
                    <button className="btn btn-primary" onClick={this.componentDidMount}> Add weather</button>
                    */}
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                <th> id</th>
                                    <th> Date</th>
                                    <th> Min Temp</th>
                                    <th> Max Temp</th>
                                    <th> Weather Type</th>
                                    <th>Temp Suggestion</th>
                                    <th>Weather Suggestion</th>
                                    <th>Wind Suggestion</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.weathers.map(
                                        weather => 
                                        <tr key = {weather.forecast_weather.id}>
                                            <td>{weather.forecast_weather.id}</td>
                                             <td> { weather.forecast_weather.date} </td>   
                                             <td> {weather.forecast_weather.mintemp}</td>
                                             <td> {weather.forecast_weather.maxtemp}</td>
                                             <td> {weather.forecast_weather.main}</td>
                                             <td> {weather.forecast_weather.decision}</td>
                                             <td> {weather.forecast_weather.raindecision}</td>
                                             <td> {weather.forecast_weather.winddecision}</td>
                                             {/*
                                             <td>
                                                 <button onClick={ () => this.editweather(weather.id)} className="btn btn-info">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteweather(weather.id)} className="btn btn-danger">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewweather(weather.id)} className="btn btn-info">View </button>
                                             </td>
                                    */}
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ListweatherComponent
