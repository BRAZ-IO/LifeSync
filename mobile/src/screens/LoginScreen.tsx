import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  StyleSheet,
  Alert,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { StackNavigationProp } from '@react-navigation/stack';
import { Button, Card } from 'react-native-paper';
import { useAuth } from '../hooks/useAuth';

type RootStackParamList = {
  Login: undefined;
  Register: undefined;
  Drawer: undefined;
};

type LoginScreenNavigationProp = StackNavigationProp<RootStackParamList, 'Login'>;

const LoginScreen: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const navigation = useNavigation<LoginScreenNavigationProp>();
  const { login } = useAuth();

  const handleLogin = async () => {
    console.log('=== HANDLE LOGIN CALLED ===');
    console.log('Email state:', email);
    console.log('Password state:', password);
    console.log('Email length:', email?.length);
    console.log('Password length:', password?.length);
    
    if (!email || !password) {
      console.log('Validation failed - empty fields');
      Alert.alert('Erro', 'Por favor, preencha todos os campos');
      return;
    }

    setLoading(true);
    try {
      console.log('=== CALLING LOGIN FUNCTION ===');
      await login(email, password);
      console.log('=== LOGIN SUCCESS - NAVIGATING ===');
      navigation.replace('Drawer');
    } catch (error: any) {
      console.log('=== LOGIN ERROR ===');
      console.log('Error object:', error);
      console.log('Error message:', error.message);
      Alert.alert('Erro de Login', error.message || 'Email ou senha inválidos');
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={styles.container}>
      <Card style={styles.card}>
        <Card.Content>
          <Text style={styles.title}>LifeSync</Text>
          <Text style={styles.subtitle}>Seu Treinador Fitness IA</Text>
          
          <TextInput
            style={styles.input}
            placeholder="Email"
            value={email}
            onChangeText={setEmail}
            keyboardType="email-address"
            autoCapitalize="none"
          />
          
          <TextInput
            style={styles.input}
            placeholder="Senha"
            value={password}
            onChangeText={setPassword}
            secureTextEntry
          />
          
          <Button
            mode="contained"
            onPress={handleLogin}
            loading={loading}
            style={styles.button}
          >
            Entrar
          </Button>
          
          <TouchableOpacity
            onPress={() => navigation.navigate('Register')}
            style={styles.linkContainer}
          >
            <Text style={styles.link}>
              Não tem uma conta? Cadastre-se
            </Text>
          </TouchableOpacity>
          
          <TouchableOpacity
            onPress={() => {
              Alert.alert(
                'Credenciais de Teste',
                'Use qualquer uma das opções abaixo:\n\n' +
                '📧 test@example.com / password\n' +
                '📧 user@lifesync.com / 123456\n' +
                '📧 admin@lifesync.com / admin\n' +
                '📧 demo@lifesync.com / demo\n\n' +
                'Ou use qualquer email + senha (mín. 4 caracteres)'
              );
            }}
            style={styles.helpContainer}
          >
            <Text style={styles.helpLink}>
              🔑 Precisa de ajuda para testar?
            </Text>
          </TouchableOpacity>
        </Card.Content>
      </Card>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#f5f5f5',
  },
  card: {
    padding: 20,
  },
  title: {
    fontSize: 32,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 8,
    color: '#6200ee',
  },
  subtitle: {
    fontSize: 16,
    textAlign: 'center',
    marginBottom: 30,
    color: '#666',
  },
  input: {
    height: 50,
    borderColor: '#ddd',
    borderWidth: 1,
    borderRadius: 8,
    marginBottom: 15,
    paddingHorizontal: 15,
    backgroundColor: '#fff',
  },
  button: {
    marginTop: 10,
    paddingVertical: 8,
  },
  linkContainer: {
    marginTop: 20,
    alignItems: 'center',
  },
  link: {
    color: '#6200ee',
    fontSize: 14,
  },
  helpContainer: {
    marginTop: 15,
    alignItems: 'center',
  },
  helpLink: {
    color: '#666',
    fontSize: 12,
    fontStyle: 'italic',
  },
});

export default LoginScreen;
