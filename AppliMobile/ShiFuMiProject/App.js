/*import { StatusBar } from 'expo-status-bar';*/
import React, {Component} from 'react';
import { StyleSheet, Text, View, Button, Image, AppRegistry, TouchableOpacity } from 'react-native';

export default class App extends Component
{
    constructor(props)
    {
        super(props);
        this.state = {
            NumPlayer:0, NumRobot:0, win: false, lose: false, draw: false,
            showShiPlay: false, showFuPlay: false, showMiPlay: false,
            showShiRob: false, showFuRob: false, showMiRob: false,
        };
    }

    onClickShi = () =>
    {
        this.setState({
            win: false, lose: false, draw: false,
            showShiPlay: false, showFuPlay: false, showMiPlay: false,
            showShiRob: false, showFuRob: false, showMiRob: false,
        });
        this.setState({showShiPlay: true});

        let robChoice = Math.floor(Math.random() * 3) + 1;
        if(robChoice === 1)
        {
            this.setState({showShiRob: true});
            this.setState({draw: true});
        }
        else if(robChoice === 2){
            this.setState({showFuRob: true});
            this.setState({lose: true});

            let scorePlayer = this.state.NumPlayer;
            let scoreRobot = this.state.NumRobot;

            this.setState({
                NumPlayer: scorePlayer,
                NumRobot: scoreRobot + 1
            });
        }
        else if(robChoice === 3)
        {
            this.setState({showMiRob: true});
            this.setState({win: true});

            let scorePlayer = this.state.NumPlayer;
            let scoreRobot = this.state.NumRobot;

            this.setState({
                NumPlayer: scorePlayer + 1,
                NumRobot: scoreRobot
            });
        }
    };
    onClickFu = () =>
    {
        this.setState({
            win: false, lose: false, draw: false,
            showShiPlay: false, showFuPlay: false, showMiPlay: false,
            showShiRob: false, showFuRob: false, showMiRob: false,
        });
        this.setState({showFuPlay: true});

        let robChoice = Math.floor(Math.random() * 3) + 1;
        if(robChoice === 1)
        {
            this.setState({showShiRob: true});
            this.setState({win: true});

            let scorePlayer = this.state.NumPlayer;
            let scoreRobot = this.state.NumRobot;

            this.setState({
                NumPlayer: scorePlayer + 1,
                NumRobot: scoreRobot
            });

        }
        else if(robChoice === 2){
            this.setState({showFuRob: true});
            this.setState({draw: true});
        }
        else if(robChoice === 3)
        {
            this.setState({showMiRob: true});
            this.setState({lose: true});
            let scorePlayer = this.state.NumPlayer;
            let scoreRobot = this.state.NumRobot;

            this.setState({
                NumPlayer: scorePlayer,
                NumRobot: scoreRobot + 1
            });
        }
    };
    onClickMi = () =>
    {
        this.setState({
            win: false, lose: false, draw: false,
            showShiPlay: false, showFuPlay: false, showMiPlay: false,
            showShiRob: false, showFuRob: false, showMiRob: false,
        });
        this.setState({showMiPlay: true});

        let robChoice = Math.floor(Math.random() * 3) + 1;
        if(robChoice === 1)
        {
            this.setState({showShiRob: true});
            this.setState({lose: true});

            let scorePlayer = this.state.NumPlayer;
            let scoreRobot = this.state.NumRobot;

            this.setState({
                NumPlayer: scorePlayer,
                NumRobot: scoreRobot + 1
            });

        }
        else if(robChoice === 2){
            this.setState({showFuRob: true});
            this.setState({win: true});

            let scorePlayer = this.state.NumPlayer;
            let scoreRobot = this.state.NumRobot;

            this.setState({
                NumPlayer: scorePlayer + 1,
                NumRobot: scoreRobot
            });
        }
        else if(robChoice === 3)
        {
            this.setState({showMiRob: true});
            this.setState({draw: true});
        }
    };


    render()
    {
        return(
          <View style={styles.container}>
              <View style={styles.header}>
                  <Text style={styles.headText}>SHIFUMI WARRIOR</Text>
              </View>
              <View style={styles.content}>
                  <View style={styles.gameInfos}>
                      <View style={styles.player}>
                          <Text style={styles.gameText}>Player</Text>
                          <Image style={styles.imgPlayer}
                          source={require('./assets/player.png')}/>
                          <Text style={styles.gameText}>Score</Text>
                          <Text style={styles.gameText}>{this.state.NumPlayer}</Text>
                      </View>
                      <View style={styles.playerChoices}>
                          {this.state.showShiPlay ?(<Image style={styles.choicePlayerShi}
                          source={require('./assets/pierre.png')}/>) :null}
                          {this.state.showFuPlay ?(<Image style={styles.choicePlayerFu}
                          source={require('./assets/feuille.png')}/>) :null}
                          {this.state.showMiPlay ?(<Image style={styles.choicePlayerMi}
                          source={require('./assets/ciseaux.png')}/>) :null}
                      </View>
                      <View style={styles.robotChoices}>
                          {this.state.showShiRob ?(<Image style={styles.choiceRobotShi}
                          source={require('./assets/pierre.png')}/>) :null}
                          {this.state.showFuRob ?(<Image style={styles.choiceRobotFu}
                          source={require('./assets/feuille.png')}/>) :null}
                          {this.state.showMiRob ?(<Image style={styles.choiceRobotMi}
                          source={require('./assets/ciseaux.png')}/>) :null}
                      </View>
                      <View style={styles.robot}>
                          <Text style={styles.gameText}>Robot</Text>
                          <Image style={styles.imgRobot}
                          source={require('./assets/robot.png')}/>
                          <Text style={styles.gameText}>Score</Text>
                          <Text style={styles.gameText}>{this.state.NumRobot}</Text>
                      </View>
                  </View>
                  <View style={styles.scoreText}>
                      {this.state.win ?(<Text style={styles.gameTextWin}>Gagné !</Text>) :null}
                      {this.state.lose ?(<Text style={styles.gameTextLose}>Perdu.</Text>) :null}
                      {this.state.draw ?(<Text style={styles.gameTextDraw}>Égalité !</Text>) :null}
                  </View>
                  <View style={styles.gameImg}>
                      <TouchableOpacity style={styles.btnShi} activeOpacity = { .5 }
                          onPress= {this.onClickShi}>
                          <Image style={styles.imgShi}
                          source={require('./assets/pierre.png')}/>
                      </TouchableOpacity>
                      <TouchableOpacity style={styles.btnFu} activeOpacity = { .5 }
                           onPress= {this.onClickFu}>
                          <Image style={styles.imgFu}
                          source={require('./assets/feuille.png')}/>
                      </TouchableOpacity>
                      <TouchableOpacity style={styles.btnMi} activeOpacity = { .5 }
                          onPress= {this.onClickMi}>
                          <Image style={styles.imgMi}
                          source={require('./assets/ciseaux.png')}/>
                      </TouchableOpacity>
                  </View>
                  <Image style={styles.rulesImg}
                  source={require('./assets/rules.png')}/>

              </View>
              <View style={styles.footer}>
                  <Text>By Christopher MM</Text>
              </View>
           /* <StatusBar style="auto" />*/
          </View>
        );
    }
}

const styles = StyleSheet.create({
  container: {
      flex: 1,
      backgroundColor: '#333',
  },
  header: {
      paddingTop: 30,
      paddingBottom: 10,
      backgroundColor: '#fff',
      alignItems: 'center',
  },
  headText: {
      fontSize: 20,
      fontWeight: 'bold',
  },
  content: {
      flex: 1,
      padding: 20,
      backgroundColor: '#333',
  },
  gameInfos: {
      flexDirection:'row',
      justifyContent:'space-between',
  },
  text: {
      color: '#fff',
  },
  gameText: {
      color: '#fff',
      fontWeight: 'bold',
  },
  gameTextWin: {
      color: '#fff',
      fontWeight: 'bold',
      position: 'absolute',
  },
  gameTextLose: {
      color: '#fff',
      fontWeight: 'bold',
      position: 'absolute',
  },
  gameTextDraw: {
      color: '#fff',
      fontWeight: 'bold',
      position: 'absolute',
  },
  player: {
      flexDirection:'column',
      alignItems: 'center',
      width: 100,
  },
  imgPlayer: {
      maxWidth: '100%',
      minHeight: 100,
      width: 100,
      height: 0,
  },
  robot: {
      flexDirection:'column',
      alignItems: 'center',
      width: 100,
  },
  imgRobot: {
      maxWidth: '100%',
      minHeight: 100,
      width: 80,
      height: 0,
  },
  gameImg: {
      marginTop: '13%',
      flexDirection:'row',
      justifyContent:'space-between',
  },
  scoreText: {
     marginTop: 100,
     justifyContent: 'center',
     textAlign: 'center',
     alignItems: 'center',
  },
  playerChoices: {

  },
  choicePlayerShi: {
      maxWidth: '100%',
      minHeight: 50,
      width: 50,
      height: 0,
      marginTop: 45,
  },
  choicePlayerFu: {
      maxWidth: '100%',
      minHeight: 50,
      width: 50,
      height: 0,
      marginTop: 45,
      //position: 'absolute',
  },
  choicePlayerMi: {
      maxWidth: '100%',
      minHeight: 50,
      width: 50,
      height: 0,
      marginTop: 45,
      //position: 'absolute',
  },
  robotChoices: {

  },
  choiceRobotShi: {
      maxWidth: '100%',
      minHeight: 50,
      width: 50,
      height: 0,
      marginTop: 45, // à modif
  },
  choiceRobotFu: {
      maxWidth: '100%',
      minHeight: 50,
      width: 50,
      height: 0,
      marginTop: 45,
      //position: 'absolute',
  },
  choiceRobotMi: {
      maxWidth: '100%',
      minHeight: 50,
      width: 50,
      height: 0,
      marginTop: 45,
      marginLeft: 10,
      //position: 'absolute',
  },
  btnShi: {
      backgroundColor:'#333',
      borderRadius:50,
      borderWidth: 3,
      borderColor: '#fff',
  },
  btnFu: {
      backgroundColor:'#333',
      borderRadius:50,
      borderWidth: 3,
      borderColor: '#fff',
  },
  btnMi: {
      backgroundColor:'#333',
      borderRadius:50,
      borderWidth: 3,
      borderColor: '#fff',
  },
  imgShi: {
      marginBottom: '100%',
      maxWidth: '100%',
      minHeight: 80,
      width: 80,
      height: 0,
  },
  imgFu: {
      maxWidth: '100%',
      minHeight: 80,
      width: 80,
      height: 0,
  },
  imgMi: {
      maxWidth: '100%',
      minHeight: 80,
      width: 80,
      height: 0,
  },
  rulesImg:{
      maxWidth: '100%',
      minHeight: 100,
      width: 100,
      height: 0,
      marginTop: '10%',
      marginRight: '35%',
      marginLeft: '35%',

  },

  footer: {
      justifyContent: 'flex-end',
      backgroundColor: '#fff',
      alignItems: 'center',
      padding: 10,
  },
});

AppRegistry.registerComponent('App', () => App);



/*
const TouchableWithoutFeedbackExample = () => {
  const [count, setCount] = useState(0);

  const onPress = () => {
    setCount(count + 1);
  };
*/

