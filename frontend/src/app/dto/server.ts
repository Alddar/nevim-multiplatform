type Nullable<T> = T | null | undefined

export class NameDTO {
  constructor(public name: string) {
  }
}

export class IdDTO {
  constructor(public id: string) {
  }
}

export class CreateLobbyDTO {
  constructor(public name: string,
              public maxPlayers: number) {
  }
}

export class LobbyListDTO {
  constructor(public lobbies: Array<{
    id: string,
    name: string,
    maxPlayers: number,
    players: number,
  }>) {
  }
}

export class LobbyDTO {
  constructor(public lobby: {
    id: string,
    name: string,
    maxPlayers: number,
    players: Array<{
      id: string,
      name: string,
      ready: boolean,
    }>
  }) {
  }
}
