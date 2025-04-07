// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { type QueryClient } from "@tanstack/react-query";
import { getApiLogs, getApiLogsUnified, getApiOverview } from "../requests/services.gen";
import * as Common from "./common";
export const ensureUseGetApiLogsData = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.ensureQueryData({ queryKey: Common.UseGetApiLogsKeyFn(clientOptions), queryFn: () => getApiLogs({ ...clientOptions }).then(response => response.data) });
export const ensureUseGetApiLogsUnifiedData = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.ensureQueryData({ queryKey: Common.UseGetApiLogsUnifiedKeyFn(clientOptions), queryFn: () => getApiLogsUnified({ ...clientOptions }).then(response => response.data) });
export const ensureUseGetApiOverviewData = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.ensureQueryData({ queryKey: Common.UseGetApiOverviewKeyFn(clientOptions), queryFn: () => getApiOverview({ ...clientOptions }).then(response => response.data) });
