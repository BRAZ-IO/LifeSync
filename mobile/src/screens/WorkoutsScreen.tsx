import React from 'react';
import { View, Text, StyleSheet, ScrollView, FlatList } from 'react-native';
import { Card, Button, Chip } from 'react-native-paper';
import { Ionicons } from '@expo/vector-icons';

interface Workout {
  id: string;
  name: string;
  category: string;
  duration: string;
  difficulty: string;
  exercises: number;
}

const workouts: Workout[] = [
  {
    id: '1',
    name: 'Peito e Tríceps',
    category: 'Força',
    duration: '45 min',
    difficulty: 'Intermediário',
    exercises: 8,
  },
  {
    id: '2',
    name: 'Cardio Intenso',
    category: 'Cardio',
    duration: '30 min',
    difficulty: 'Avançado',
    exercises: 5,
  },
  {
    id: '3',
    name: 'Pernas e Glúteos',
    category: 'Força',
    duration: '50 min',
    difficulty: 'Intermediário',
    exercises: 10,
  },
];

const WorkoutsScreen: React.FC = () => {
  const renderWorkout = ({ item }: { item: Workout }) => (
    <Card style={styles.card}>
      <Card.Content>
        <View style={styles.cardHeader}>
          <Text style={styles.workoutName}>{item.name}</Text>
          <Chip style={styles.categoryChip}>{item.category}</Chip>
        </View>
        
        <View style={styles.workoutInfo}>
          <View style={styles.infoItem}>
            <Ionicons name="time" size={16} color="#666" />
            <Text style={styles.infoText}>{item.duration}</Text>
          </View>
          <View style={styles.infoItem}>
            <Ionicons name="fitness" size={16} color="#666" />
            <Text style={styles.infoText}>{item.exercises} exercícios</Text>
          </View>
        </View>
        
        <View style={styles.difficultyContainer}>
          <Text style={styles.difficultyLabel}>Dificuldade:</Text>
          <Text style={styles.difficulty}>{item.difficulty}</Text>
        </View>
        
        <Button
          mode="contained"
          style={styles.startButton}
          onPress={() => console.log('Starting workout:', item.id)}
        >
          Iniciar Treino
        </Button>
      </Card.Content>
    </Card>
  );

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Treinos</Text>
        <Button mode="outlined" compact>
          + Novo
        </Button>
      </View>
      
      <ScrollView style={styles.content}>
        <FlatList
          data={workouts}
          renderItem={renderWorkout}
          keyExtractor={(item) => item.id}
          scrollEnabled={false}
        />
      </ScrollView>
      
      <View style={styles.fabContainer}>
        <Button
          mode="contained"
          style={styles.fab}
          onPress={() => console.log('AI Recommendation')}
        >
          <Ionicons name="sparkles" size={20} color="white" />
        </Button>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 20,
    backgroundColor: 'white',
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333',
  },
  content: {
    flex: 1,
    padding: 15,
  },
  card: {
    marginBottom: 15,
  },
  cardHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 10,
  },
  workoutName: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
    flex: 1,
  },
  categoryChip: {
    backgroundColor: '#6200ee',
  },
  workoutInfo: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 10,
  },
  infoItem: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  infoText: {
    marginLeft: 5,
    fontSize: 14,
    color: '#666',
  },
  difficultyContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 15,
  },
  difficultyLabel: {
    fontSize: 14,
    color: '#666',
    marginRight: 5,
  },
  difficulty: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#6200ee',
  },
  startButton: {
    marginTop: 5,
  },
  fabContainer: {
    position: 'absolute',
    bottom: 20,
    right: 20,
  },
  fab: {
    width: 56,
    height: 56,
    borderRadius: 28,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default WorkoutsScreen;
