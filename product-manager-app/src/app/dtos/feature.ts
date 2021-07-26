export interface Feature{

    id?: number, 
	displayName?: string,
	technicalName: string;
    expiresOn?: Date;
	description?: string;
	inverted: boolean;
	archived?: boolean;
	customerIds?: string[];
}