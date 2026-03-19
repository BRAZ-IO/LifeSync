import React from 'react';
import { View, Text, StyleSheet, ScrollView } from 'react-native';
import { Card, Button, FAB } from 'react-native-paper';
import { Ionicons } from '@expo/vector-icons';

const DashboardScreen: React.FC = () => {
  return (
    <ScrollView style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.greeting}>Olá, Usuário!</Text>
        <Text style={styles.subtitle}>Vamos atingir seus objetivos hoje</Text>
      </View>

      <Card style={styles.card}>
        <Card.Content>
          <View style={styles.cardHeader}>
            <Ionicons name="fitness" size={24} color="#6200ee" />
            <Text style={styles.cardTitle}>Treino de Hoje</Text>
          </View>
          <Text style={styles.cardContent}>Peito e Tríceps</Text>
          <Button mode="contained" style={styles.cardButton}>
            Iniciar Treino
          </Button>
        </Card.Content>
      </Card>

      <Card style={styles.card}>
        <Card.Content>
          <View style={styles.cardHeader}>
            <Ionicons name="checkmark-circle" size={24} color="#4CAF50" />
            <Text style={styles.cardTitle}>Hábitos do Dia</Text>
          </View>
          <Text style={styles.cardContent}>3 de 5 completos</Text>
          <View style={styles.habitProgress}>
            <View style={styles.progressBar} />
          </View>
        </Card.Content>
      </Card>

      <Card style={styles.card}>
        <Card.Content>
          <View style={styles.cardHeader}>
            <Ionicons name="stats-chart" size={24} color="#FF9800" />
            <Text style={styles.cardTitle}>Progresso Semanal</Text>
          </View>
          <Text style={styles.cardContent}>4 treinos concluídos</Text>
          <Text style={styles.cardContent}>+2.5kg força média</Text>
        </Card.Content>
      </Card>

      <Card style={styles.card}>
        <Card.Content>
          <View style={styles.cardHeader}>
            <Ionicons name="trophy" size={24} color="#FFD700" />
            <Text style={styles.cardTitle}>Conquistas</Text>
          </View>
          <Text style={styles.cardContent}>🔥 7 dias seguidos</Text>
          <Text style={styles.cardContent}>💪 Primeiro mês completo</Text>
        </Card.Content>
      </Card>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  header: {
    padding: 20,
    backgroundColor: '#6200ee',
  },
  greeting: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'white',
  },
  subtitle: {
    fontSize: 16,
    color: 'rgba(255, 255, 255, 0.8)',
    marginTop: 4,
  },
  card: {
    margin: 15,
    marginHorizontal: 15,
  },
  cardHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },
  cardTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginLeft: 10,
  },
  cardContent: {
    fontSize: 16,
    color: '#666',
    marginBottom: 5,
  },
  cardButton: {
    marginTop: 10,
  },
  habitProgress: {
    height: 8,
    backgroundColor: '#e0e0e0',
    borderRadius: 4,
    marginTop: 10,
  },
  progressBar: {
    height: '100%',
    width: '60%',
    backgroundColor: '#4CAF50',
    borderRadius: 4,
  },
});

export default DashboardScreen;
